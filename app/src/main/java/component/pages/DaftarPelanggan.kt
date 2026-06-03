package component.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.praktam2_2417051059.data.Model.Pelanggan
import com.example.praktam2_2417051059.data.Model.SourcePelanggan

@Composable
fun DaftarPelanggan(navController: NavController) {
    // buat nyimpen teks yang diketik di searchbar
    var searchQuery by remember { mutableStateOf("") }

    // Scaffold berguna banget buat naruh Floating Button dan Bottom Bar otomatis!
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Nanti arahin ke form tambah pelanggan */ },
                containerColor = Color(0xFFC09982),
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah")
            }
        }
        // Catatan: Kodingan Bottom Bar kamu nanti tinggal ditaruh di sini
        // bottomBar = { BottomNavigationBar(...) } 
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9F9F9)) // Background abu-abu super soft
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            // 1. Judul Halaman
            Text(
                text = "Buku Pelanggan",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB58A73) // Warna cokelat teks judul
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 2. Search Bar (Kolom Pencarian)
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { teksBaru -> searchQuery = teksBaru }, // Update state tiap ngetik
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Cari nama pelanggan...", color = Color.Gray) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
                },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFB58A73),
                    unfocusedBorderColor = Color(0xFFE0E0E0),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 3. Daftar Pelanggan (LazyColumn)
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // LOGIKA KEREN: Filter otomatis data pelanggan berdasarkan teks search bar!
                val pelangganYangDicari = SourcePelanggan.daftarPelanggan.filter {
                    it.nama.contains(searchQuery, ignoreCase = true)
                }

                // Render item yang udah difilter
                items(pelangganYangDicari) { pelanggan ->
                    ItemPelangganCard(pelanggan = pelanggan, navController = navController)
                }
            }
        }
    }
}

@Composable
fun ItemPelangganCard(pelanggan: Pelanggan, navController: NavController) {
    // Ambil 1 huruf pertama dari nama buat ditaruh di lingkaran inisial
    val inisial = pelanggan.nama.take(1).uppercase()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // Nanti tinggal buka comment ini kalau navigasinya udah siap
                 navController.navigate("detail_ukuran/${pelanggan.nama}")
            },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Lingkaran Inisial Kiri
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFEAE5D9)), // Warna cokelat krem pastel
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = inisial,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF8B6B56)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Nama & Nomor HP di Tengah
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = pelanggan.nama,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = pelanggan.noHp,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            // Ikon Panah di Kanan
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Detail",
                tint = Color.Gray
            )
        }
    }
}