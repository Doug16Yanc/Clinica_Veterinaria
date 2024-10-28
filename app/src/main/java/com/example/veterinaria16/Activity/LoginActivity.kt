package com.example.veterinaria16.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import clinica.veterinaria16.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var intent : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val auth = FirebaseAuth.getInstance()

        binding.redefinirSenha.setOnClickListener {
            intent = Intent(this@LoginActivity, ResetPasswordActivity::class.java)
            startActivity(intent)
            Toast.makeText(
                this,
                "Encaminhando para tela de redefinição de senha!",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.registrarCliente.setOnClickListener {
            intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Encaminhando para tela de registro!", Toast.LENGTH_SHORT).show()
        }


        fun validateFields(): Boolean {
            val isUsernameValid = binding.editText1.text.isNotEmpty() && binding.editText1.text.contains("@") &&
                    (binding.editText1.text.contains("gmail") ||
                            binding.editText1.text.contains("hotmail") ||
                            binding.editText1.text.contains("outlook") ||
                            binding.editText1.text.contains("yahoo") ||
                            binding.editText1.text.contains("live") ||
                            binding.editText1.text.contains("icloud") ||
                            binding.editText1.text.contains("mail"))

            binding.editText1.error = if (isUsernameValid) null else "Email inválido"

            val isPasswordValid = binding.editText2.text.isNotEmpty() && binding.editText2.text.length >= 6
            binding.editText2.error =
                if (isPasswordValid) null else "A senha deve ter no mínimo 6 caracteres"

            if (isUsernameValid && isPasswordValid) {
                return true
            }
            else {
                return false
            }
        }

        binding.loginBtn.setOnClickListener {

            if (validateFields()) {
                binding.loginBtn.visibility = View.VISIBLE
                binding.loginBtn.visibility = View.GONE

                auth.signInWithEmailAndPassword(
                    binding.editText1.text.toString(),
                    binding.editText2.text.toString()
                )
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this@LoginActivity, IntroActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(applicationContext, "Login inválido", Toast.LENGTH_SHORT)
                                .show()
                            binding.loginBtn.visibility = View.VISIBLE
                        }
                    }
            }
        }
    }
}