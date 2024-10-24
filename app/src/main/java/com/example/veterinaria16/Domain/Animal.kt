package com.example.veterinaria16.Domain

import com.example.veterinaria16.Domain.enums.Category
import com.example.veterinaria16.Domain.enums.SexAnimal
import java.util.Date

data class Animal(
    val Id : Int = 0,
    val Name : String = "",
    val category : Category?,
    val sex : SexAnimal?,
    val weight : Double = 0.0,
    val birthday : Date?
)