package component.pages
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.praktam2_2417051059.Model.SourcePemesanan

@Composable
fun PesananScreen(navController: NavController) {
    val daftarPesanan = SourcePemesanan.listPemesanan.sortedBy { it.deadline }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
            .padding(vertical = 20.dp)
    ) {
        Text(
            text = "Daftar Pesanan",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            items(daftarPesanan) { pesanan ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("detailPesanan/${pesanan.id}") },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Lingkaran Inisial
                        Box(
                            modifier = Modifier
                                .size(45.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFEAE5D9)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = pesanan.namaPelanggan.take(1).uppercase(),
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF8B6B56)
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        // Nama & Baju
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = pesanan.namaPelanggan,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Baju: ${pesanan.namaBaju}",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }

                        // Deadline
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "DEADLINE",
                                fontSize = 10.sp,
                                color = Color.Gray
                            )
                            Surface(
                                color = Color(0xFFFEECE9),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = pesanan.deadline,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFFD35400)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
