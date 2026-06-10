package com.example.praktam2_2417051059.Model

import androidx.compose.runtime.mutableStateListOf

object SourcePemesanan {
    val listPemesanan = mutableStateListOf(
        Pemesanan(
            id = "1",
            namaPelanggan = "Mbak Sisca",
            namaBaju = "Rok Polos",
            tanggalPesan = "2026-06-08",
            deadline = "2026-06-11",
            catatan = "Minta karet pinggang agak dilonggarin dikit biar nggak sesak.",
            status = "Aktif"
        ),
        Pemesanan(
            id = "2",
            namaPelanggan = "Mbak Rara",
            namaBaju = "Atasan Polos",
            tanggalPesan = "2026-06-05",
            deadline = "2026-06-15",
            catatan = "Bagian lengan tolong dibikin agak ngembang (puff) sedikit.",
            status = "Aktif"
        ),
        Pemesanan(
            id = "3",
            namaPelanggan = "Ibu Dina",
            namaBaju = "Dress Brokat",
            tanggalPesan = "2026-06-01",
            deadline = "2026-06-25",
            catatan = "Payetnya dibanyakin di bagian dada sama ujung lengan aja.",
            status = "Aktif"
        ),
        Pemesanan(
            id = "4",
            namaPelanggan = "Pak Budi",
            namaBaju = "Kaos Polos",
            tanggalPesan = "2026-06-30",
            deadline = "2026-07-30",
            catatan = "Lengan ditipisin, dibuat estetik",
            status = "Selesai"
        )
    )
}