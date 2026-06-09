package component.pages

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.praktam2_2417051059.data.Model.SourcePelanggan

@Composable
fun DetailPelanggan(navController: NavController, namaPelanggan: String?) {
    val pelanggan = SourcePelanggan.daftarPelanggan.find { it.nama == namaPelanggan }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- Header & Tombol Back ---
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Kembali")
            }
            Text(text = "Profil Pelanggan", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(30.dp))

        // --- profil inisial ---
        Box(modifier = Modifier.size(100.dp).clip(CircleShape).background(Color(0xFFEAE5D9)), contentAlignment = Alignment.Center) {
            Text(text = pelanggan?.nama?.take(1) ?: "", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color(0xFF8B6B56))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = pelanggan?.nama ?: "Tidak Dikenal", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 4.dp)) {
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "Telepon",
                modifier = Modifier.size(16.dp),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = pelanggan?.noHp ?: "-", color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = "Detail Ukuran Badan", fontWeight = FontWeight.Bold, color = Color(0xFFB58A73))
                Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color(0xFFF1F1F1))
                Text(
                    text = pelanggan?.ukuran ?: "Data ukuran belum ada.",
                    style = MaterialTheme.typography.bodyLarge,
                    lineHeight = 24.sp
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                navController.navigate("katalog/${pelanggan?.nama}")
            },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC09982))
        ) {
            Text(text = "Buat Pesanan Baru",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp)
        }
    }
}