package com.example.veterinaria16.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import clinica.veterinaria16.databinding.ActivityRegisterBinding
import com.example.veterinaria16.Domain.Customer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.mindrot.jbcrypt.BCrypt

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    //private lateinit var auth : FirebaseAuth
    private lateinit var intent : Intent

    var auth = FirebaseAuth.getInstance()
    var database = FirebaseDatabase.getInstance().getReference("Customer")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val register = binding.registerBtn
        val cancel = binding.cancelBtn

        register.setOnClickListener {
            val name = binding.name.text.toString().trim()
            val cpf = binding.cpf.text.toString().trim()
            val telephone = binding.telephone.text.toString().trim()
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (validateName() && validateCPF() && validateTelephone() && validateEmail() && validatePassword() && validateConfirmPassword()) {
                registerUser(name, cpf, telephone, email, password)
            } else {
                Toast.makeText(
                    this,
                    "Por favor, preencha todos os campos corretamente!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        cancel.setOnClickListener {
            intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun registerUser(
        name: String,
        cpf: String,
        telephone: String,
        email: String,
        password: String
    ) {

        val id = (10000..900000).random()


        if (binding.password.text.toString() == binding.confirmPassword.text.toString()) {


            auth.createUserWithEmailAndPassword(
                binding.email.text.toString(),
                binding.password.text.toString()
            )

                .addOnCompleteListener(this){
                    task ->
                    if (task.isSuccessful) {
                        val passwordHash = BCrypt.hashpw(password, BCrypt.gensalt())
                        val customer = Customer(id, name, cpf, telephone, email, passwordHash, mutableListOf())
                        database.child(id.toString()).setValue(customer).addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    this,
                                    "Cliente registrado com sucesso",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this, "Erro ao salvar dados de cliente.", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
        }
        clearFields()
    }

    fun validateName(): Boolean {
        val isNameValid = binding.name.text.isNotEmpty()

        if (isNameValid) {
            return true
        } else {
            Toast.makeText(this, "Nome não pode ser inválido!", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    fun validateCPF(): Boolean {
        val isCPFValid = binding.cpf.text.isNotEmpty()/* || binding.cpf.text.toString().length != 11 || !binding.cpf.text.toString()
            .matches(Regex("\\d+"))*/

        if (isCPFValid) {
            return true
        } else {
            Toast.makeText(this, "CPF inválido.", Toast.LENGTH_LONG).show()
            return false
        }
    }

    fun clearFields() {
        binding.name.text?.clear()
        binding.cpf.text?.clear()
        binding.telephone.text?.clear()
        binding.email.text?.clear()
        binding.password.text?.clear()
        binding.confirmPassword.text?.clear()
    }

    fun validateTelephone(): Boolean {
        var valid: Boolean
        val isTelephoneValid = binding.telephone.text.toString()
            .isNotEmpty() /*|| binding.telephone.text.toString().length < 10 || !binding.telephone.text.toString()
            .matches(Regex("\\d+"))*/

        if (isTelephoneValid) {
            return true
        } else {
            Toast.makeText(this, "Telefone inválido.", Toast.LENGTH_LONG).show()
            return false
        }
    }

    fun validateEmail(): Boolean {
        val email = binding.email
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
        } else {
            Toast.makeText(this, "Email inválido!", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    fun validatePassword(): Boolean {
        val password = binding.password
        val isPasswordValid =
            password.text.isNotEmpty() && password.text.length >= 6
          //  password.text.isNotEmpty() && password.text.length <= 12 && password.text.any { it.isUpperCase() } && password.text.any { it.isDigit() }

        if (isPasswordValid) {
            return true
        } else {
            Toast.makeText(
                this,
                "A senha deve ter no mínimo 12 caracteres, conter pelo menos uma letra maiúscula e um dígito numérico.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
    }

    fun validateConfirmPassword(): Boolean {
        val isConfirmPassword = binding.confirmPassword.text.isNotEmpty()
        val isPasswordMatch = binding.password.text.toString() == binding.confirmPassword.text.toString()

        if (isConfirmPassword && isPasswordMatch) {
            return true
        } else {
            if (!isConfirmPassword) {
                Toast.makeText(
                    this,
                    "A confirmação de senha não pode ser vazia.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this, "Senhas incoerentes!", Toast.LENGTH_SHORT).show()
            }
            return false
        }
    }
}