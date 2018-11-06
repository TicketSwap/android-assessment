package com.ticketswap.assessment.network.client

import com.squareup.moshi.Moshi
import okhttp3.*
import java.io.IOException

/**
 * simple network client based on the okhttpclient, moshi and rxjava
 */
class NetworkClient(private val okHttpClient: OkHttpClient, private val moshi: Moshi,
                    private val baseUrl: String) {
    fun <T, P> request(url: String, method: String,
                       queryParams: Map<String, String>, body: T?, bodyClass: Class<T>?, responseClass: Class<P>,
                       mediaType: String = "application/json; charset=utf-8") =
            parseResponse(okHttpClient.newCall(
                    buildRequest(url, method, queryParams,
                            if (bodyClass != null) moshi.adapter(bodyClass).toJson(body) else null, mediaType)
            ).execute(), responseClass)

    private fun <P> parseResponse(res: Response?, responseClass: Class<P>): P {
        val body = res?.body() ?: throw IOException()
        if (res.isSuccessful) {
            return moshi.adapter(responseClass).fromJson(body.string())!!
        }

        throw HttpException(res.code(), body.string())
    }

    private fun buildRequest(url: String, method: String,
                             queryParams: Map<String, String>, body: String?,
                             mediaType: String = "application/json; charset=utf-8"): Request =
            Request.Builder().url(HttpUrl.parse(baseUrl)!!.newBuilder()!!.apply {
                this.addEncodedPathSegment(url)
                queryParams.forEach { addEncodedQueryParameter(it.key, it.value) }
            }.build()).apply {
                if (method != "GET" && body != null)
                    method(method, RequestBody.create(MediaType.parse(mediaType), body))
            }.build()
}

class HttpException(val code: Int, val body: String) : Exception()