package component.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.praktam2_2417051059.R
import com.example.praktam2_2417051059.data.Model.Pakaian
import com.example.praktam2_2417051059.data.repository.PakaianRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DaftarBajuScreen(navController: NavController, namaPelanggan: String? = null, onPakaianLoaded: (List<Pakaian>) -> Unit = {}) {
    val snackbarHostState = remember { SnackbarHostState() }
    var pakaianList by remember { mutableStateOf<List<Pakaian>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    val repository = remember { PakaianRepository() }

    LaunchedEffect(Unit) {
        try {
            pakaianList = repository.getPakaian()
            onPakaianLoaded(pakaianList)
            isLoading = false
        } catch (e: Exception) {
            isLoading = false
            isError = true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column{
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF81C784))
                }
            } else if(isError || pakaianList.isEmpty()){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Gagal Memuat Data",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Pastikan koneksi internet Anda menyala",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    HeaderDaftarBaju()
                }

                Spacer(modifier = Modifier.height(10.dp))
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.LightGray))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFF1F1F1)),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(pakaianList) { pakaian ->
                        ItemBaju(
                            pakaian = pakaian,
                            navController = navController,
                            namaPelanggan = namaPelanggan,
                            snackbarHostState = snackbarHostState
                        )
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun HeaderDaftarBaju(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
            .padding(vertical = 10.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "back",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .size(23.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Katalog & Layanan",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = "Pilih dasar jahitan pesanan",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Box() {
            IconButton(
                onClick = {},
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    }
}

@Composable
fun ItemBaju(pakaian: Pakaian, navController: NavController, namaPelanggan: String?, snackbarHostState: SnackbarHostState) {
    var isFavorite by remember { mutableStateOf(false) }
    var isDeleting by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Box() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("detail/${pakaian.nama}")
                },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFE8E8E8)),
            elevation = CardDefaults.cardElevation(3.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Box() {
                    AsyncImage(
                        model = pakaian.imageUrl,
                        contentDescription = pakaian.nama,
                        placeholder = painterResource(id = R.drawable.ic_launcher_background),
                        error = painterResource(id = R.drawable.ic_launcher_background),
                        modifier = Modifier
                            .size(110.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )

                    IconButton(
                        onClick = { isFavorite = !isFavorite },
                        modifier = Modifier.align(Alignment.TopStart)
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite Icon",
                            tint = if (isFavorite) Color.Red else Color.White
                        )
                    }
                }
                Spacer(Modifier.width(20.dp))
                Column(
                    modifier = Modifier.height(110.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column() {
                        Text(
                            text = "${pakaian.nama}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Rp ${pakaian.harga}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    isDeleting = true
                                    delay(2000)
                                    isDeleting = false

                                    snackbarHostState.showSnackbar("${pakaian.nama} berhasil dihapus dari katalog")
                                }
                            },
                            modifier = Modifier.size(40.dp),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(0.dp),
                            enabled = !isDeleting,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red,
                                disabledContainerColor = Color(0xFFFFAAAA)
                            )
                        ) {
                            if (isDeleting) {
                                // Animasi Muter
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    color = Color.White,
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "delete",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        Button(
                            onClick = {
                                // langsung pindah halaman ngebawa nama bajunya
                                navController.navigate("detail/${namaPelanggan ?: "Umum"}/${pakaian.nama}")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                        ) {
                            Text(
                                text = "Pilih Baju",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            }
        }
    }
}