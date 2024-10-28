package com.example.veterinaria16.Activity

import android.os.Bundle
import android.widget.Toast
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
        val item = intent.getParcelableExtra<AnimalCategory>("Object")
        if (item != null) {
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
        } else {
            Toast.makeText(this, "Erro ao carregar detalhes do animal", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}