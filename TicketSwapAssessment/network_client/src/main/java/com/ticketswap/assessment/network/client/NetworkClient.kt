package com.ticketswap.assessment.network.client

import com.squareup.moshi.Moshi
import okhttp3.*
import java.io.IOException

class NetworkClient(private val okHttpClient: OkHttpClient, private val moshi: Moshi) {
    fun <T, P> request(url: String, method: String,
                       queryParams: Map<String, String>, body: T?, bodyClass: Class<T>, responseClass: Class<P>,
                       mediaType: String = "application/json; charset=utf-8") =
            parseResponse(okHttpClient.newCall(
                    buildRequest(url, method, queryParams, moshi.adapter(bodyClass).toJson(body), mediaType)
            ).execute(), responseClass)

    private fun <P> parseResponse(res: Response?, responseClass: Class<P>): P? {
        if (res?.body() == null) {
            throw IOException()
        }
        if (res.isSuccessful) {
            return moshi.adapter(responseClass).fromJson(res.body()!!.string())
        }

        throw HttpException(res.code(), res.body().toString())
    }

    private fun buildRequest(url: String, method: String,
                             queryParams: Map<String, String>, body: String?,
                             mediaType: String = "application/json; charset=utf-8"): Request =
            Request.Builder().url(HttpUrl.parse(url)!!.newBuilder()!!.apply {
                queryParams.forEach { addQueryParameter(it.key, it.value) }
            }.build()).apply {
                if (method != "GET" && body != null)
                    method(method, RequestBody.create(MediaType.parse(mediaType), body))
            }.build()
}

class HttpException(val code: Int, val body: String) : Exception()