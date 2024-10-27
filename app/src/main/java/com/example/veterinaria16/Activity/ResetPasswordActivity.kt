package com.example.veterinaria16.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import clinica.veterinaria16.databinding.ActivityResetPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        var email = binding.email

        binding.resetBtn.setOnClickListener {
            val registerActivity = RegisterActivity()
            val isEmailValid = registerActivity.validateEmail()
            if (isEmailValid) {
                auth.sendPasswordResetEmail(email.text.toString())
                    .addOnCompleteListener {
                        task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Por favor, verifique seu email a fim de redefinir sua senha.", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
            }
            else{
                Toast.makeText(this, "Email inválido!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.returnBtn.setOnClickListener {
            val intent = Intent(this@ResetPasswordActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}