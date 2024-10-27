package com.example.veterinaria16.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import clinica.veterinaria16.databinding.ActivityDetailAnimalBinding
import com.bumptech.glide.Glide
import com.example.veterinaria16.Domain.AnimalCategory

class DetailAnimalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailAnimalBinding
    private lateinit var item : AnimalCategory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundle()
    }

    private fun getBundle() {
        item = intent.getParcelableExtra("Object") ?: return

        binding.apply {
            curiosity.text = item.Name
            textCuriosity.text = item.Curiosity
            back.setOnClickListener {
                finish()
            }

            Glide.with(this@DetailAnimalActivity)
                .load(item.Image)
                .into(binding.animal)
        }
    }
}