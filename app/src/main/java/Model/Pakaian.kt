package Model

import androidx.annotation.DrawableRes

data class Pakaian(
    val nama: String,
    val harga: Int,
    @DrawableRes val ImageRes: Int
)
