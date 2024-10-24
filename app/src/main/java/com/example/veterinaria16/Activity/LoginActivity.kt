package com.example.veterinaria16.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import clinica.veterinaria16.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var intent : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            val input1 = binding.editText1.text.toString().trim()
            val input2 = binding.editText2.text.toString().trim()

            if (input1.isNotEmpty() && input2.isNotEmpty()) {
                intent  = Intent(this@LoginActivity, IntroActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Login realizado com sucesso, seja bem-vindo(a), caro(a)" + input1, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Campos de texto não podem estar vazios!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.registrarCliente.setOnClickListener {
            intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Encaminhando para tela de registro!", Toast.LENGTH_SHORT).show()
        }
    }
}