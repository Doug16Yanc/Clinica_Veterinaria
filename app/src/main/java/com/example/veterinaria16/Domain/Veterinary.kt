package com.example.veterinaria16.Domain

import android.os.Parcel
import android.os.Parcelable

data class Veterinary(
    val Address : String = "",
    val Biography : String = "",
    val Experience : Int = 0,
    private val Id : Int = 0,
    val Telephone : String = "",
    val Name : String = "",
    val Image : String = "",
    val Rating : Double = 0.0,
    val Site : String = ""
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Address)
        parcel.writeString(Biography)
        parcel.writeInt(Experience)
        parcel.writeInt(Id)
        parcel.writeString(Telephone)
        parcel.writeString(Name)
        parcel.writeString(Image)
        parcel.writeDouble(Rating)
        parcel.writeString(Site)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Veterinary> {
        override fun createFromParcel(parcel: Parcel): Veterinary {
            return Veterinary(parcel)
        }

        override fun newArray(size: Int): Array<Veterinary?> {
            return arrayOfNulls(size)
        }
    }

}
