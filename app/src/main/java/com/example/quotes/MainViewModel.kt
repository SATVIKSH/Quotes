package com.example.quotes

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quotes.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainViewModel(val context: Context):ViewModel() {

    var list= emptyArray<Quote>()
    var quote=MutableLiveData<Quote>()
    var index =0
    init{
        list=loadQuoteList()
        quote.value=list[index]
    }

    private fun loadQuoteList(): Array<Quote> {
       val inputStream=context.assets.open("quotes.json")
        val size=inputStream.available()
        val buffer=ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json=String(buffer,Charsets.UTF_8)
        val gson= Gson()
        return gson.fromJson(json,Array<Quote>::class.java)
    }


    fun getNextQuote() {

        quote.value=list[++index]

    }
    fun getPreviousQuote()
    {
        if(index!=0)
         quote.value=list[--index]
    }
}