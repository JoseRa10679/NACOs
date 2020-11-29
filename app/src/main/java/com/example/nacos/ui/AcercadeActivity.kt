package com.example.nacos.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nacos.databinding.ActivityAcercadeBinding

class AcercadeActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityAcercadeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnAcerca.setOnClickListener{ finish()}
    }
}
