package component.pages
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.praktam2_2417051059.Model.SourcePemesanan
import com.example.praktam2_2417051059.data.Model.SourcePelanggan

@Composable
fun DetailPesanan(navController: NavController, pesananId: String?) {
    val pesanan = SourcePemesanan.listPemesanan.find { it.id == pesananId }
    val pelanggan = SourcePelanggan.daftarPelanggan.find { it.nama == pesanan?.namaPelanggan }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
            }
            Text("Detail Pesanan", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                // Nama & No HP
                Text("Pelanggan", fontSize = 12.sp, color = Color.Gray)
                Text(pesanan?.namaPelanggan ?: "-", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(pelanggan?.noHp ?: "-", color = Color(0xFF8B6B56))

                Divider(modifier = Modifier.padding(vertical = 16.dp), color = Color(0xFFF1F1F1))

                // tanggal & deadline
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text("Tgl Pesan", fontSize = 12.sp, color = Color.Gray)
                        Text(pesanan?.tanggalPesan ?: "-")
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("Deadline", fontSize = 12.sp, color = Color.Gray)
                        Text(pesanan?.deadline ?: "-", fontWeight = FontWeight.Bold, color = Color(0xFFD35400))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // CARD DETAIL BAJU & CATATAN
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text ="Pesanan: ${pesanan?.namaBaju}",
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text ="Catatan:",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = pesanan?.catatan ?: "Tidak ada catatan tambahan.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // CARD UKURAN
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFDF9F0)), // Warna beda dikit biar fokus
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color(0xFFEAE5D9))
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Info, contentDescription = null, tint = Color(0xFF8B6B56), modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Ukuran Badan", fontWeight = FontWeight.Bold, color = Color(0xFF8B6B56))
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = pelanggan?.ukuran ?: "Data ukuran belum ada.",
                    style = MaterialTheme.typography.bodyLarge,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}