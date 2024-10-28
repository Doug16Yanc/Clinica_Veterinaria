package com.example.veterinaria16.Activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
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

        fun validateFields() {
            val isEmailValid = email.text.isNotEmpty() &&
                    email.text.contains("@") &&
                    (email.text.contains("gmail") ||
                            email.text.contains("hotmail") ||
                            email.text.contains("outlook") ||
                            email.text.contains("yahoo") ||
                            email.text.contains("live") ||
                            email.text.contains("icloud") ||
                            email.text.contains("mail"))

            email.error = if (isEmailValid) null else "Email inválido"

        }

        binding.email.afterTextChanged { validateFields() }

        binding.resetBtn.setOnClickListener {
            binding.resetBtn.visibility = View.GONE

            auth.sendPasswordResetEmail(email.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this,
                            "Verifique seu email para redefinir sua senha",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Erro: " + task.exception?.message, Toast.LENGTH_LONG)
                            .show()
                        binding.resetBtn.visibility = View.VISIBLE
                    }
                }
        }

        /* binding.resetBtn.setOnClickListener {
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
        } */
        binding.returnBtn.setOnClickListener {
            val intent = Intent(this@ResetPasswordActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

}