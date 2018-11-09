package com.ticketswap.assessment.spotify

import com.google.gson.Gson
import com.ticketswap.assessment.BuildConfig
import com.ticketswap.assessment.spotify.entity.Token
import io.reactivex.Observable
import io.reactivex.ObservableSource
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.Callable


class SpotifyAuthApi {

    fun token(auth: String, grandType: String): Observable<Token> {

        return Observable.defer(object : Callable<ObservableSource<Token>> {
            override fun call(): ObservableSource<Token> {
                val client = OkHttpClient()
                        .newBuilder()
                        .addInterceptor(interceptor(auth))
                        .addInterceptor(cacheInterceptor())
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                        })
                        .build()
                try {
                    val httpUrl = HttpUrl.Builder()
                            .scheme(Constants.API_SCHEME)
                            .host(Constants.API_HOST_AUTH)
                            .addPathSegment(Constants.API_PATH_API)
                            .addPathSegment(Constants.API_PATH_TOKEN)
                            .build()
                    val requestBody: RequestBody = FormBody.Builder().add(Constants.API_REQUEST_FIELD_GRANT_TYPE, grandType).build()
                    val response = client.newCall(Request.Builder().url(httpUrl.url()).post(requestBody).build()).execute()
                    return Observable.just<Token>(Gson().fromJson(response.body()?.string(), Token::class.java))
                } catch (e: IOException) {
                    return Observable.error(e)
                }
            }
        })
    }

    private fun interceptor(auth: String) : Interceptor {
        return Interceptor() { chain ->
            val request = chain.request().newBuilder()
                    .addHeader("Authorization", auth)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build()
            chain.proceed(request)
        }
    }

    private fun cacheInterceptor() : Interceptor {
        return Interceptor() { chain ->
            val request = chain.request().newBuilder()
                    .build()
            val response = chain.proceed(request)
            response
        }

    }
}