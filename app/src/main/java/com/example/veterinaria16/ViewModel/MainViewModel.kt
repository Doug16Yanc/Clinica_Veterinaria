package com.example.veterinaria16.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.veterinaria16.Domain.AnimalCategory
import com.example.veterinaria16.Domain.Veterinary
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel() : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _category = MutableLiveData<MutableList<AnimalCategory>>()
    private val _veterinary = MutableLiveData<MutableList<Veterinary>>()

    val category : LiveData<MutableList<AnimalCategory>> = _category
    val veterinary : LiveData<MutableList<Veterinary>> = _veterinary

    fun loadCategory() {
        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val categories = mutableListOf<AnimalCategory>()
                    for (categorySnapshot in snapshot.children) {
                        val category = categorySnapshot.getValue(AnimalCategory::class.java)
                        if (category != null) {
                            categories.add(category)
                        }
                    }
                    _category.value = categories
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainViewModel", "Erro ao carregar categorias: ${error.message}")
            }

        })
    }

    fun loadVeterinaries() {
        val Ref = firebaseDatabase.getReference("Veterinary")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val veterinaries = mutableListOf<Veterinary>()

                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(Veterinary::class.java)

                    if (list != null) {
                        veterinaries.add(list)
                    }
                }
                _veterinary.value = veterinaries
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainViewModel", "Erro ao carregar veterinários: ${error.message}")

            }
        })
    }
}