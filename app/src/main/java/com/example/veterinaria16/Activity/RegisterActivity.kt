package com.example.veterinaria16.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import clinica.veterinaria16.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var intent : Intent

    private val name = binding.name
    private val cpf = binding.cpf
    private val telephone = binding.telephone
    private val email = binding.email
    private val password = binding.password
    private val confirmPassword = binding.confirmPassword
    private val register = binding.registerBtn
    private val cancel = binding.cancelBtn

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        register.setOnClickListener {
            val name = binding.name.text.toString().trim()
            val cpf = binding.cpf.text.toString().trim()
            val telephone = binding.telephone.text.toString().trim()
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (validateName() && validateCPF() && validateTelephone() && validateEmail() && validatePassword() && validateConfirmPassword()) {
                registerUser(name, cpf, telephone, email, password)
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos corretamente!", Toast.LENGTH_SHORT).show()
            }
        }
        cancel.setOnClickListener {
            intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerUser(name: String, cpf: String, telephone: String, email: String, password: String) {
        clearFields()
    }

    private fun validateName(): Boolean {
        val isNameValid = name.text.isNotEmpty()

        if (isNameValid) {
            return true
        }
        else {
            Toast.makeText(this, "Nome não pode ser inválido!", Toast.LENGTH_SHORT).show()
            return false
        }
    }
    private fun validateCPF() : Boolean {
        val isCPFValid = cpf.text.toString().isEmpty() || cpf.text.toString().length != 11 || !cpf.text.toString()
            .matches(Regex("\\d+"))

        if (isCPFValid) {
            return true
        }
        else{
            Toast.makeText(this, "CPF inválido.", Toast.LENGTH_SHORT).show()
            return false
        }
    }
    private fun clearFields() {
        name.text?.clear()
        cpf.text?.clear()
        telephone.text?.clear()
        email.text?.clear()
        password.text?.clear()
        confirmPassword.text?.clear()
    }
   private fun validateTelephone() : Boolean{
       var valid : Boolean
       val isTelephoneValid = telephone.text.toString()
           .isEmpty() || telephone.text.toString().length < 10 || !telephone.text.toString()
           .matches(Regex("\\d+"))

       if (isTelephoneValid) {
           return true
       }
       else {
           Toast.makeText(this, "Telefone inválido.", Toast.LENGTH_SHORT).show()
           return false
       }
   }
    private fun validateEmail(): Boolean {
        val isEmailValid = email.text.isNotEmpty() &&
                email.text.contains("@") &&
                (email.text.contains("gmail") ||
                        email.text.contains("hotmail") ||
                        email.text.contains("outlook") ||
                        email.text.contains("yahoo") ||
                        email.text.contains("live") ||
                        email.text.contains("icloud") ||
                        email.text.contains("mail"))

        if (isEmailValid) {
            return true
        }
        else {
            Toast.makeText(this, "Email inválido!", Toast.LENGTH_SHORT).show()
            return false
        }
    }
    private fun validatePassword(): Boolean {
        val isPasswordValid = password.text.isNotEmpty() && password.text.length <= 12 && password.text.any { it.isUpperCase() } && password.text.any { it.isDigit()}

        if (isPasswordValid) {
            return true
        }
        else {
            Toast.makeText(this, "A senha deve ter no mínimo 12 caracteres, conter pelo menos uma letra maiúscula e um dígito numérico.", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    private fun validateConfirmPassword(): Boolean {
        val isConfirmPassword = confirmPassword.text.isNotEmpty()
        val isPasswordMatch = password.text.toString() == confirmPassword.text.toString()

        if (isConfirmPassword && isPasswordMatch) {
            return true
        }
        else {
            if (!isConfirmPassword) {
                Toast.makeText(this, "A confirmação de senha não pode ser vazia.", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Senhas incoerentes,", Toast.LENGTH_SHORT).show()
            }
            return false
        }
    }
}