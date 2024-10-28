package com.example.veterinaria16.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import clinica.veterinaria16.databinding.ActivityIntroBinding
import com.example.veterinaria16.Adapter.CategoryAdapter
import com.example.veterinaria16.Adapter.TopVeterinariesAdapter
import com.example.veterinaria16.ViewModel.MainViewModel

class IntroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategory()
        initTopVeterinaries()

        binding.settings.setOnClickListener {
            val intent = Intent(this@IntroActivity, SettingsActivity::class.java)
            startActivity(intent)
        }
        binding.animals.setOnClickListener {
            val intent = Intent(this@IntroActivity, RegisterAnimalActivity::class.java)
            startActivity(intent)
        }
        binding.logout.setOnClickListener {
            val intent = Intent(this@IntroActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initCategory() {
        binding.apply {
            progressBar1.visibility = View.VISIBLE
            viewModel.category.observe(this@IntroActivity, Observer {
                binding.viewCategory.layoutManager =
                    LinearLayoutManager(this@IntroActivity, LinearLayoutManager.HORIZONTAL, false)
                binding.viewCategory.adapter = CategoryAdapter(it)
                binding.progressBar1.visibility = View.GONE
            })
            viewModel.loadCategory()
        }
    }

    private fun initTopVeterinaries() {
        binding.apply {
            progressBar2.visibility = View.VISIBLE
            viewModel.veterinary.observe(this@IntroActivity, Observer {
                binding.viewCategory1.layoutManager =
                    LinearLayoutManager(this@IntroActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.viewCategory1.adapter = TopVeterinariesAdapter(it)
                binding.progressBar2.visibility = View.GONE
            })
            viewModel.loadVeterinaries()
        }
    }
}