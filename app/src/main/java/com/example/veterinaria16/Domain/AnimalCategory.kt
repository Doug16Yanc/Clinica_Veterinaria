package com.example.veterinaria16.Domain

import android.os.Parcel
import android.os.Parcelable

data class AnimalCategory(
    val Id : Int = 0,
    val Name : String = "",
    val Curiosity : String = "",
    val Image: String = ""
) : Parcelable {
   constructor(parcel : Parcel) : this (
       parcel.readInt(),
       parcel.readString().toString(),
       parcel.readString().toString(),
       parcel.readString().toString()
   )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(Id)
        parcel.writeString(Name)
        parcel.writeString(Curiosity)
        parcel.writeString(Image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnimalCategory> {
        override fun createFromParcel(parcel: Parcel): AnimalCategory {
            return AnimalCategory(parcel)
        }

        override fun newArray(size: Int): Array<AnimalCategory?> {
            return arrayOfNulls(size)
        }
    }
}
