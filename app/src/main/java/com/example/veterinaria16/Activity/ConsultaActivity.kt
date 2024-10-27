package com.example.veterinaria16.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import clinica.veterinaria16.databinding.ActivityConsultaBinding

class ConsultaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConsultaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConsultaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}