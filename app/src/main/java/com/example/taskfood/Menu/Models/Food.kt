package com.example.taskfood.Menu.Models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Food(
    @SerializedName("name") var name: String?,
    @SerializedName("desc") var desc: String?,
    @SerializedName("price") var price: Double?,
    @SerializedName("sizeText") var sizeText: String?,
    @SerializedName("photoLink") var photoLink: String?,
    @SerializedName("category") var category: String?
) : Serializable,Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(desc)
        parcel.writeValue(price)
        parcel.writeString(sizeText)
        parcel.writeString(photoLink)
        parcel.writeString(category)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Food> {
        override fun createFromParcel(parcel: Parcel): Food {
            return Food(parcel)
        }

        override fun newArray(size: Int): Array<Food?> {
            return arrayOfNulls(size)
        }
    }
}