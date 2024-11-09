package com.example.veterinaria16.Activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import clinica.veterinaria16.R
import clinica.veterinaria16.databinding.ActivityRegisterAnimalBinding
import com.example.veterinaria16.Domain.Animal
import com.example.veterinaria16.Domain.enums.Category
import com.example.veterinaria16.Domain.enums.SexAnimal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class RegisterAnimalActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterAnimalBinding
    private lateinit var intent : Intent
    private var database = FirebaseDatabase.getInstance().getReference("Customer")
    private var auth = FirebaseAuth.getInstance()
    private var currentUser = auth.currentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        var register = binding.registerBtn
        var cancel = binding.cancelBtn

        if (currentUser != null) {
            val customerId = currentUser!!.uid

            register.setOnClickListener {
                val name = binding.name.text.toString().trim()
                val birthday = binding.birthday.text.toString().trim()
                val weight = binding.weight.text.toString().toDouble()
                val category = identifyCategory()
                val sex = identifySexAnimal()

                if (validateWeight() && validateBirthdayAnimal()) {
                    registerAnimal(customerId, name, weight, birthday, category, sex)
                } else {
                    Toast.makeText(
                        this,
                        "Por favor, preencha todos os campos corretamente!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            cancel.setOnClickListener {
                intent = Intent(this@RegisterAnimalActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun identifyCategory() : Category? {
        val category = when (binding.speciesRadioGroup.checkedRadioButtonId) {
            R.id.dogRadioButton -> Category.DOG
            R.id.catRadioButton -> Category.CAT
            R.id.birdRadioButton -> Category.BIRD
            R.id.livestockRadioButton -> Category.LIVESTOCK
            R.id.snackRadioButton -> Category.SNACK
            R.id.lagomorphRadioButton -> Category.LAGOMORPH
            R.id.rodentRadioButton -> Category.RODENT
            else -> null
        }
        return category
    }

    private fun identifySexAnimal() : SexAnimal? {
        val sex = when (binding.sexRadioGroup.checkedRadioButtonId) {
            R.id.maleRadioButton -> SexAnimal.MALE
            R.id.femaleRadioButton -> SexAnimal.FEMALE
            else -> null
        }
        return sex
    }
    private fun registerAnimal(customerId: String, name: String, weight: Double, birthday: String, category: Category?, sex: SexAnimal?) {
        val id = (10000..900000).random()
        val customerRef = database.child(id.toString())

        val animalId = customerRef.child("mutableList").push().key
        if (animalId != null) {
            val animal = Animal(customerId.hashCode(), id, name, weight, birthday, category, sex)

            customerRef.child("mutableList").child(animalId).setValue(animal)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        customerRef.child("name").get().addOnSuccessListener { snapshot ->
                            val customerName = snapshot.value as? String
                            Toast.makeText(
                                this,
                                "Animal registrado com sucesso para o cliente ${customerName}!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } else {
                        Toast.makeText(
                            this,
                            "Erro ao registrar o animal.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
        clearFields()
    }

    fun clearFields() {
        binding.name.text?.clear()
        binding.weight.text?.clear()
        binding.speciesRadioGroup.clearCheck()
        binding.sexRadioGroup.clearCheck()
    }

    private fun validateBirthdayAnimal(): Boolean {
        binding.birthday.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    val adjustedMonth = selectedMonth + 1

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val selectedDate = LocalDate.of(selectedYear, adjustedMonth, selectedDay)
                        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        binding.birthday.text = selectedDate.format(formatter)
                    } else {
                        val formattedDate = String.format(
                            Locale.getDefault(),
                            "%02d/%02d/%04d",
                            selectedDay,
                            adjustedMonth,
                            selectedYear
                        )
                        binding.birthday.text = formattedDate
                    }
                },
                year, month, day
            )
            datePickerDialog.show()
        }
        return true
    }


    private fun validateWeight(): Boolean {
        return try {
            val weight = binding.weight.text.toString().toDouble()
            weight > 0
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "O peso deve ser um número válido.", Toast.LENGTH_SHORT).show()
            false
        }
    }


}