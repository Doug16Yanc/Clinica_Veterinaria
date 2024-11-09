package com.example.veterinaria16.Domain

import android.os.Parcel
import android.os.Parcelable

data class AnimalCategory(
    val Curiosity : String = "",
    val Id : Int = 0,
    val Image: String = "",
    val Name : String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Curiosity)
        parcel.writeInt(Id)
        parcel.writeString(Image)
        parcel.writeString(Name)
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
