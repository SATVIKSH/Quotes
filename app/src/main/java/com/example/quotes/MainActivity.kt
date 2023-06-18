package com.example.quotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import com.example.quotes.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val viewModel: MainViewModel = ViewModelProvider(this,ViewModelFactory(applicationContext)).get(MainViewModel::class.java)
        viewModel.quote.observe(this, Observer {
            binding.quotes=it
        })


        binding.tvNext.setOnClickListener{
           viewModel.getNextQuote()


        }
        binding.tvPrevious.setOnClickListener{
            viewModel.getPreviousQuote()

        }
        binding.fabShare.setOnClickListener{
            val intent= Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, binding.quotes?.text)
            startActivity(intent)
        }
    }


}