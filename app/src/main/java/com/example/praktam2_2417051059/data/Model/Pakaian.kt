package com.example.praktam2_2417051059.data.Model

import com.google.gson.annotations.SerializedName

data class Pakaian(
    @SerializedName("nama")
    val nama: String,
    @SerializedName("harga")
    val harga: Int,
    @SerializedName("image_url")
    val imageUrl: String
)
