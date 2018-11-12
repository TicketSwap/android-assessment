package com.ticketswap.assessment

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

abstract class AbstractDetailActivity<T>() : AppCompatActivity() {

    class IntentFactory<T>(private val clazz: Class<T>) {
        val jsonAdapter : JsonAdapter<T> = Moshi.Builder().build().adapter(clazz)

        fun createIntent(context: Context, t: T) : Intent {
            val intent = Intent(context, ArtistActivity::class.java)
            //intent.putExtra("artist", artist)
            intent.putExtra("KEY", toJson(t))
            return intent
        }

        private fun toJson(t: T) : String {
            return jsonAdapter.toJson(t)
        }

        fun fromJson(intent: Intent) : T? {
            return jsonAdapter.fromJson(intent.extras.getString("KEY"))
        }
    }

}
