package com.example.praktam2_2417051059.data.repository
import com.example.praktam2_2417051059.data.Model.Pakaian
import com.example.praktam2_2417051059.data.network.RetrofitClient

class PakaianRepository {
    suspend fun getPakaian(): List<Pakaian> {
        return try {
            RetrofitClient.instance.getPakaian()
        } catch (e: Exception) {
            emptyList()
        }
    }
}