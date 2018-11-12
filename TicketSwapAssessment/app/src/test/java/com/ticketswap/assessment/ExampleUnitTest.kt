package com.ticketswap.assessment

import com.ticketswap.assessment.spotify.SpotifyApi
import io.mockk.every
import io.mockk.mockk
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private lateinit var spotifyApi : SpotifyApi

    @Before
    fun setup() {

        val client = mockk<OkHttpClient>()
        val call = mockk<Call>()
        val response = mockk<Response>()
        val body = mockk<ResponseBody>()
        every { body.string() } returns SearchResultData.json1
        every { response.body() } returns body
        every { call.execute() } returns response
        every { client.newCall(any()) } returns call

        spotifyApi = SpotifyApi(client)
    }

    @Test
    fun test_tracks() {
        val searchResponse = spotifyApi.searchSpotify("..", "").blockingFirst()
        assertEquals(searchResponse.tracks?.items?.size, 2)    }

    @Test
    fun test_artist() {
        val searchResponse = spotifyApi.searchSpotify("..", "").blockingFirst()
        assertEquals(searchResponse.tracks?.items?.getOrNull(0)?.artists?.getOrNull(0)?.name, "Hinkstep")
    }
}
