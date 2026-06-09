package component.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

// Pastikan lokasi import ini sesuai dengan struktur folder kamu
import com.example.praktam2_2417051059.data.Model.Pelanggan
import com.example.praktam2_2417051059.data.Model.SourcePelanggan

@Composable
fun FormTambahPelanggan(navController: NavController) {
    var nama by remember { mutableStateOf("") }
    var noHp by remember { mutableStateOf("") }
    var ukuran by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9F7F2))
                .padding(padding)
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // --- HEADER CUSTOM BIASA (Pengganti TopAppBar) ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.offset(x = (-12).dp) // Digeser sedikit biar sejajar kiri
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                }

                Text(
                    text = "Tambah Pelanggan",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

                // Spacer ini fungsinya sbg penyeimbang tombol back, biar teks beneran di tengah
                Spacer(modifier = Modifier.size(48.dp))
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Data Pelanggan Baru",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF8B6B56),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // --- INPUT NAMA ---
            OutlinedTextField(
                value = nama,
                onValueChange = { nama = it },
                label = { Text("Nama Lengkap") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- INPUT NO HP ---
            OutlinedTextField(
                value = noHp,
                onValueChange = { noHp = it },
                label = { Text("Nomor Handphone (WhatsApp)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- INPUT UKURAN ---
            OutlinedTextField(
                value = ukuran,
                onValueChange = { ukuran = it },
                label = { Text("Detail Ukuran Badan") },
                placeholder = { Text("Contoh: LD: 100cm, PB: 135cm, PL: 55cm") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                shape = RoundedCornerShape(12.dp),
                minLines = 3,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            // --- TOMBOL SIMPAN ---
            Button(
                onClick = {
                    if (nama.isNotBlank() && noHp.isNotBlank() && ukuran.isNotBlank()) {
                        val pelangganBaru = Pelanggan(nama = nama, noHp = noHp, ukuran = ukuran)
                        SourcePelanggan.daftarPelanggan.add(pelangganBaru)
                        navController.popBackStack()
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Semua kolom data wajib diisi!")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC09982))
            ) {
                Text(
                    "Simpan Data",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}