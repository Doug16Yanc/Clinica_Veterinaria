package com.example.veterinaria16.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import clinica.veterinaria16.databinding.ActivityRegisterAnimalBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterAnimalActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterAnimalBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var intent : Intent

    private var name = binding.name
    private var birthday = binding.dataNascimento
    private var weight = binding.peso
    private var category = binding.speciesRadioGroup
    private var category1 = binding.moreSpeciesRadioGroup
    private var sex = binding.sexRadioGroup
    private var register = binding.registerBtn
    private var cancel = binding.cancelBtn

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        register.setOnClickListener {
            val name = binding.name.text.toString().trim()
            val birthday = binding.dataNascimento.toString().trim()
            val weight = binding.peso.toString().trim()
            val category = binding.speciesRadioGroup.toString().trim()
            val category1 = binding.moreSpeciesRadioGroup.toString().trim()
            val sex = binding.sexRadioGroup.toString().trim()

            if (validateWeight()) {
                registerAnimal(name, birthday, weight, category, sex)
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos corretamente!", Toast.LENGTH_SHORT).show()
            }
        }
        cancel.setOnClickListener {
            intent = Intent(this@RegisterAnimalActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun registerAnimal(name: String, birthday: String, weight: String, category: String, sex: String) {

    }

    private fun validateWeight() : Boolean {
        val isValidWeight = weight.text.any { it.isDigit()}

        if (isValidWeight) {
            return true
        }
        else {
            Toast.makeText(this, "O peso deve conter números", Toast.LENGTH_SHORT).show()
            return false
        }
    }

}