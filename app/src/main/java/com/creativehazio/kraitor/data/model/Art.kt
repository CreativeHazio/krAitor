package com.creativehazio.kraitor.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Art(

    @SerializedName("user")
    val user : String,

    @SerializedName("n")
    val n: Int,

    @SerializedName("prompt")
    val prompt: String,

    @SerializedName("size")
    val size: String

)