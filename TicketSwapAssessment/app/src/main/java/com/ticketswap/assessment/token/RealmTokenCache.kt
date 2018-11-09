package com.ticketswap.assessment.token

import android.content.SharedPreferences
import com.google.gson.Gson
import com.ticketswap.assessment.spotify.entity.Token
import javax.inject.Inject


class RealmTokenCache @Inject
constructor(private val sharedPreferences: SharedPreferences) : TokenCache {

    private val KEY_TOKEN : String = "token"

    override fun getToken(): Token? {
        val tokenGson = sharedPreferences.getString(KEY_TOKEN, null)
        val gson = Gson()
        return gson.fromJson(tokenGson, Token::class.java)
    }

    override fun saveToken(token: Token) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val jsonToken = gson.toJson(token)

        editor.putString(KEY_TOKEN, jsonToken)
        editor.commit()
    }
}