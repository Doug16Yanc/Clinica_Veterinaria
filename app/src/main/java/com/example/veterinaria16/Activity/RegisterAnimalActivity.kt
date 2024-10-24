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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var register = binding.registerBtn
        var cancel = binding.cancelBtn

        register.setOnClickListener {
            val name = binding.name.text.toString().trim()
            val birthday = binding.birthday.toString().trim()
            val weight = binding.weight.toString().trim()
            val category = binding.speciesRadioGroup.toString().trim()
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
        val isValidWeight = binding.weight.text.any { it.isDigit()}

        if (isValidWeight) {
            return true
        }
        else {
            Toast.makeText(this, "O peso deve conter números", Toast.LENGTH_SHORT).show()
            return false
        }
    }

}