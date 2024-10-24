package com.example.veterinaria16.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import clinica.veterinaria16.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.example.veterinaria16.Domain.Veterinary

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item : Veterinary
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundle()
    }

    private fun getBundle() {
        item = intent.getParcelableExtra("Object")!!

        binding.apply {
            textView1.text = item.Name
            textView2.text = item.Address
            textView3.text = item.Experience.toString()+ " anos"
            textView4.text = item.Rating.toString()
            textView8.text = item.Biography
            back.setOnClickListener {
                finish()
            }

            website.setOnClickListener {
                val i = Intent(Intent.ACTION_VIEW)
                i.setData(Uri.parse(item.Site))
                startActivity(i)
            }

            call.setOnClickListener {
                val uri = "Número : " + item.Telephone.trim()
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse(uri))
                startActivity(intent)
            }

            message.setOnClickListener {
                val uri = Uri.parse("smsto ${item.Telephone}")
                val intent = Intent(Intent.ACTION_SEND, uri)
                intent.putExtra("sms_body", "Texto SMS")
                startActivity(intent)
            }
            share.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_SUBJECT, item.Name)
                intent.putExtra(Intent.EXTRA_TEXT, item.Name + " " + item.Address + " " + item.Telephone)
                startActivity(Intent.createChooser(intent, "Escolha:"))
            }

            Glide.with(this@DetailActivity)
                .load(item.Image)
                .into(binding.doctor)
        }
    }
}