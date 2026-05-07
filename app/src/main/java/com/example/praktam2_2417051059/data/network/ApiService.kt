package com.example.praktam2_2417051059.data.network

import com.example.praktam2_2417051059.data.Model.Pakaian
import retrofit2.http.GET

interface ApiService {
    @GET("daftar_pakaian.json")
    suspend fun getPakaian(): List<Pakaian>
}