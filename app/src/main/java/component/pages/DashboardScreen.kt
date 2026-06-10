package component.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.praktam2_2417051059.Model.Pemesanan
import com.example.praktam2_2417051059.Model.SourcePemesanan
import com.example.praktam2_2417051059.data.Model.Pelanggan
import com.example.praktam2_2417051059.data.Model.SourcePelanggan

@Composable
fun DashboardScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 10.dp)
            .padding(horizontal = 20.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(vertical = 20.dp)
            ) {
                Text(
                    text = "Selamat datang,"
                )
                Text(
                    text = "Zahra Kebaya",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)

            ) {
                Card(
                    modifier = Modifier
                        .weight(1f),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "85",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Pesanan\nAktif",
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .weight(1f),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "85",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Pesanan\nSelesai",
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .weight(1f),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "85",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Total\nPelanggan",
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Deadline sebentar lagi")

                    IconButton(
                        onClick ={

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "deadline",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

                val pesananTerurut = SourcePemesanan.listPemesanan.sortedBy { it.deadline }
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(pesananTerurut) { pemesanan ->
                        DeadlineBaju(pemesanan = pemesanan,navController = navController)
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Pesanan Terbaru")

                    IconButton(
                        onClick ={
                            navController.navigate("daftarBaju"){
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "deadline",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(SourcePemesanan.listPemesanan){ pemesanan ->
                        PesananBaju(pemesanan = pemesanan, navController = navController)
                    }
                }

            }
        Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Pelanggan Anda")

                    IconButton(
                        onClick ={
                            navController.navigate("daftarPelanggan"){
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "pelanggan",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    SourcePelanggan.daftarPelanggan.take(5).forEach { pelanggan ->
                        Pelanggan(pelanggan = pelanggan, navController = navController)
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
            }
        }

    }
}

@Composable
fun DeadlineBaju(pemesanan: Pemesanan, navController: NavController){
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        modifier = Modifier
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color.Black.copy(alpha = 0.5f),
                spotColor = Color.Black.copy(alpha = 0.5f)
            )
            .clickable{
                navController.navigate("detailPesanan/${pemesanan.id}")
            }

    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .padding(vertical = 15.dp)
                .width(170.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = pemesanan.deadline,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(5.dp))
                    .background(color = MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 10.dp)
                    .padding(vertical = 5.dp)

            )
            Text(
                text = pemesanan.namaBaju,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = pemesanan.namaPelanggan
            )
        }

    }
}
@Composable
fun PesananBaju(pemesanan: Pemesanan, navController: NavController) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
        modifier = Modifier
            .clickable {
                navController.navigate("detailPesanan/${pemesanan.id}")
            }
        ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .padding(vertical = 15.dp)
                .width(170.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "${pemesanan.namaPelanggan}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "${pemesanan.deadline}",
                fontSize = 14.sp
            )

            Row(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(5.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${pemesanan.namaBaju}",
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

    }
}

@Composable
fun Pelanggan (pelanggan: Pelanggan, navController: NavController){
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        modifier = Modifier
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color.Black.copy(alpha = 0.5f),
                spotColor = Color.Black.copy(alpha = 0.5f)
            )
            .clickable{
                navController.navigate("detailPelanggan/${pelanggan.nama}")
            }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .padding(vertical = 15.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = pelanggan.nama,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = pelanggan.noHp
            )
        }

    }
}
