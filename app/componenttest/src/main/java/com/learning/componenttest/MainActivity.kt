package com.learning.componenttest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learning.componenttest.databinding.ActivityMainBinding
import com.learning.componenttest.service.MyService

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        binding.serviceOn.setOnClickListener {
            startService(Intent(this, MyService::class.java))
        }
        binding.serviceOff.setOnClickListener {
            stopService(Intent(this,MyService::class.java))
        }
    }
}