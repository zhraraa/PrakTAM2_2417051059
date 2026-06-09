package component.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.praktam2_2417051059.Model.Pemesanan
import com.example.praktam2_2417051059.Model.SourcePemesanan
import com.example.praktam2_2417051059.R
import com.example.praktam2_2417051059.data.Model.Pakaian
import com.example.praktam2_2417051059.data.Model.SourcePelanggan
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DetailBaju(pakaian: Pakaian, namaPelanggan: String?, navController: NavController) {
    // 1. CARI UKURAN OTOMATIS: Narik data dari dummy SourcePelanggan yang udah kita buat
    val detailUkuran = SourcePelanggan.daftarPelanggan.find { it.nama == namaPelanggan }?.ukuran ?: "Ukuran tidak ditemukan"

    // 2. STATE BARU: Cuma butuh state buat catatan tambahan, karena nama & ukuran udah fix
    var catatanTambahan by remember { mutableStateOf("") }

    var isFavorite by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
                .verticalScroll(rememberScrollState()) // Biar bisa scroll kalau keyboard muncul
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                AsyncImage(
                    model = pakaian.imageUrl,
                    contentDescription = pakaian.nama,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    error = painterResource(id = R.drawable.ic_launcher_background)
                )
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp)
                        .background(Color.White.copy(alpha = 0.7f), RoundedCornerShape(50))
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Kembali",
                        tint = Color.Black
                    )
                }
            }

            Column {
                Text(
                    text = pakaian.nama,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Rp ${pakaian.harga}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Text(
                text = "Area Catatan Penjahit",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 10.dp)
            )

            // NAMA PELANGGAN (Read-Only)
            OutlinedTextField(
                value = namaPelanggan ?: "",
                onValueChange = {  },
                readOnly = true,
                label = { Text("Nama Pelanggan") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // DETAIL UKURAN (Read-Only & Otomatis)
            OutlinedTextField(
                value = detailUkuran,
                onValueChange = {  },
                readOnly = true,
                label = { Text("Detail Ukuran") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
            )

            // CATATAN TAMBAHAN (Bisa Diketik)
            OutlinedTextField(
                value = catatanTambahan,
                onValueChange = { catatanTambahan = it },
                label = { Text("Catatan Tambahan (Opsional)") },
                placeholder = { Text("Misal: Jangan ketat di perut") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        isLoading = true
                        delay(2000)
                        val pesananBaru = Pemesanan(
                            id = System.currentTimeMillis().toString(),
                            namaPelanggan = namaPelanggan ?: "Umum",
                            namaBaju = pakaian.nama,
                            tanggalPesan = "2026-06-09",
                            deadline = "2026-06-15",
                            catatan = ""
                        )

                        // SIMPAN KE DATABASE DUMMY
                        SourcePemesanan.listPemesanan.add(pesananBaru)

                        // MUNCULIN NOTIF
                        snackbarHostState.showSnackbar(
                            "Pesanan ${pakaian.nama} untuk $namaPelanggan berhasil dicatat!"
                        )

                        // PINDAH HALAMAN
                        isLoading = false
                        navController.navigate("dashboardScreen") {
                            popUpTo("daftarBaju") { inclusive = true }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = !isLoading,
                shape = RoundedCornerShape(12.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Menyimpan...")
                } else {
                    Text("Simpan Pesanan", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }

            TextButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Kembali ke Katalog", color = Color.Gray)
            }
        }
    }
}