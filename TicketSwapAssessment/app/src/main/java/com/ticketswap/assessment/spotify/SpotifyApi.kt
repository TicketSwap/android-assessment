package com.ticketswap.assessment.spotify

import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.ObservableSource
import okhttp3.*
import java.io.IOException
import java.util.*
import java.util.concurrent.Callable

class SpotifyApi(val client : OkHttpClient) {

    fun searchSpotify(query: String, type: String): Observable<SearchResponse> {

        return Observable.defer(object : Callable<ObservableSource<SearchResponse>> {
            override fun call(): ObservableSource<SearchResponse> {
                if (query == null || query.isEmpty()) {
                    return Observable.just(SearchResponse())
                }
                val httpUrl = HttpUrl.Builder()
                        .scheme(Constants.API_SCHEME)
                        .host(Constants.API_HOST)
                        .addPathSegment(Constants.API_VERSION)
                        .addPathSegment(Constants.API_PATH_SEARCH)
                        .addQueryParameter(Constants.API_REQUEST_PARAM_QUERY, query)
                        .addQueryParameter(Constants.API_REQUEST_PARAM_TYPE, type)
                        .build()
                try {
                    val response = client.newCall(Request.Builder().url(httpUrl.url()).get().build()).execute()
                    return Observable.just<SearchResponse>(Gson().fromJson(response.body()?.string(), SearchResponse::class.java))
                } catch (e: IOException) {
                    return Observable.error(e)
                }
            }
        })
    }

    fun convert(response : SearchResponse) : ArrayList<String> {
        // Combine the artists and tracks
        val arrayList = ArrayList<String>()
        val names: List<String>? = response.artists?.items?.map { "Artist: ${it.name}" }
        if (names != null) arrayList.addAll(names.asIterable())
        val tracks = response.tracks?.items?.map { "Track: ${it.name}" }
        if (tracks != null) arrayList.addAll(tracks)
        return arrayList
    }


}