package com.example.veterinaria16.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import clinica.veterinaria16.databinding.ActivityAnimalBinding

class AnimalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}