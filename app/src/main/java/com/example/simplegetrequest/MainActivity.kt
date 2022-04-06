package com.example.simplegetrequest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.simplegetrequest.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var queue: RequestQueue
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        queue = Volley.newRequestQueue(this)


        binding.btnUpdatePokemon.setOnClickListener{
            var num: Int = binding.etPokemonAmount.text.toString().toInt()

            getPokemonList(num)

        }

    }

    fun getPokemonList(listAmount: Int){
        val url = "https://pokeapi.co/api/v2/pokemon/?limit=${listAmount}"
        //val url = "https://pokeapi.co/api/v2/pokemon/?limit=10"

        val jsonRequest = JsonObjectRequest(url, Response.Listener<JSONObject>{ response ->
            Log.i("JSONRESPONSE", response.getJSONArray("results").toString())
        },
        Response.ErrorListener { error ->
            Log.w("JSONRESPONSE", error.message as String)

        })

        queue.add(jsonRequest)

    }

    override fun onStop() {
        super.onStop()
        queue.cancelAll("Stopped")
    }
}