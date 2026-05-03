package com.example.praktam2_2417051059

import com.example.praktam2_2417051059.Model.Pakaian
import com.example.praktam2_2417051059.Model.Pemesanan
import com.example.praktam2_2417051059.Model.SourcePemesanan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import coil.compose.AsyncImage


import com.example.praktam2_2417051059.ui.theme.PrakTAM2_2417051059Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrakTAM2_2417051059Theme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}
@Composable
fun AppNavigation (navController: NavHostController) {
    var pakaianList by remember { mutableStateOf<List<Pakaian>>(emptyList()) }

    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home"){
            DaftarBajuScreen(navController = navController) { fetchedPakaian ->
                pakaianList = fetchedPakaian
            }
        }

        composable("detail/{nama}") { backStackEntry ->
            val nama = backStackEntry.arguments?.getString("nama")

            val pakaian = pakaianList.find {
                it.nama == nama
            }

            if (pakaian != null) {
                DetailScreen(pakaian = pakaian, navController = navController, isFullScreen = true)
            }
        }
    }
}

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 10.dp)
            .padding(horizontal = 20.dp)
    ) {
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
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Deadline sebentar lagi")

                IconButton(
                    onClick ={}
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "deadline",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Row() {
                DeadlineBaju()
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Pesanan Terbaru")

                IconButton(
                    onClick ={}
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
                items(SourcePemesanan.daftarPemesanan){ pemesanan ->
                    PesananBaju(pemesanan = pemesanan)
                }
            }

        }

    }
}

@Composable
fun DeadlineBaju(){
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        border = BorderStroke(width = 1.dp, Color.Black)

    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .padding(vertical = 15.dp)
                .width(170.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "2 hari lagi",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(5.dp))
                    .background(color = MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 10.dp)
                    .padding(vertical = 5.dp)

            )
            Text(
                text = "Dress Polos",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Rara"
            )
        }

    }
}
@Composable
fun PesananBaju(pemesanan: Pemesanan) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),

    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .padding(vertical = 15.dp)
                .width(170.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "${pemesanan.nama}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "${pemesanan.tanggal}",
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
                    text = "${pemesanan.jenisPakaian}",
                    color = MaterialTheme.colorScheme.onSurface
                )

                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(25.dp),
                    shape = RoundedCornerShape(5.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(
                        text = "Detail",
                        fontSize = 10.sp,
                        color = Color.Black
                    )
                }
            }
        }

    }
}

@Composable
fun DaftarBajuScreen(navController: NavController, onPakaianLoaded: (List<Pakaian>) -> Unit = {}) {
    var pakaianList by remember { mutableStateOf<List<Pakaian>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        try {
            pakaianList = com.example.praktam2_2417051059.network.RetrofitClient.instance.getPakaian()
            onPakaianLoaded(pakaianList)
            isLoading = false
        } catch (e: Exception) {
            isLoading = false
            isError = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            HeaderDaftarBaju()
        }

        Spacer(modifier = Modifier.height(10.dp))
        Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))
        Spacer(modifier = Modifier.height(25.dp))

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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF1F1F1)),
                contentPadding = PaddingValues(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(pakaianList) { pakaian ->
                    ItemBaju(pakaian = pakaian, navController = navController)
                }
            }
        }

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
fun ItemBaju(pakaian: Pakaian, navController: NavController) {
    var isFavorite by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    val caroutineScope = rememberCoroutineScope  ()
    val snackbarHostState = remember { SnackbarHostState() }

    Box(){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    // Navigasi saat card diklik
                    navController.navigate("detail/${pakaian.nama}")
                },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFE8E8E8)),
            elevation = CardDefaults.cardElevation(3.dp)
        ) {
            Row (
                modifier = Modifier.padding(16.dp)
            ){
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
                        modifier = Modifier
                            .align(Alignment.TopStart)
                    ){
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite Icon",
                            tint = if (isFavorite) Color.Red else Color.White
                        )
                    }
                }
                Spacer(Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .height(110.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column() {
                        Text(
                            text = "${pakaian.nama}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(text = "${pakaian.harga}")
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {},
                            modifier = Modifier.size(40.dp),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        // Tombol Tambah
                        Button(
                            onClick = {
                                caroutineScope.launch {
                                    isLoading = true
                                    delay(2000)
                                    snackbarHostState.showSnackbar("Pesanan ${pakaian.nama} berhasil ditambahkan!")
                                    isLoading = false
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = !isLoading,
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                        ) {
                            if (isLoading){
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    strokeWidth = 2.dp

                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Menambahkan",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontSize = 11.sp
                                )
                            } else {
                                Text(
                                    text = "+ Tambah",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }

                        }
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
fun DetailScreen(pakaian: Pakaian, navController: NavController, isFullScreen: Boolean = false){
    var isFavorite by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    val caroutineScope = rememberCoroutineScope  ()
    val snackbarHostState = remember { SnackbarHostState() }

    Box(modifier = Modifier.fillMaxWidth()){
        Column() {
            Card(
                modifier = Modifier
                    .width(600.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFE8E8E8)),
                elevation = CardDefaults.cardElevation(3.dp)
            ) {
                Row (
                    modifier = Modifier.padding(16.dp)
                ){
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
                            modifier = Modifier
                                .align(Alignment.TopStart)
                        ){
                            Icon(
                                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favorite Icon",
                                tint = if (isFavorite) Color.Red else Color.White
                            )
                        }
                    }
                    Spacer(Modifier.width(20.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(110.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column() {
                            Text(
                                text = "${pakaian.nama}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(text = "${pakaian.harga}")
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = {},
                                modifier = Modifier.size(40.dp),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit",
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(15.dp))

                            Button(
                                onClick = {
                                    caroutineScope.launch {
                                        isLoading = true
                                        delay(2000)
                                        snackbarHostState.showSnackbar("Pesanan ${pakaian.nama} berhasil ditambahkan!")
                                        isLoading = false
                                    }
                                },
                                modifier = Modifier.fillMaxWidth(),
                                enabled = !isLoading,
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                ),
                            ) {
                                if (isLoading){
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(20.dp),
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        strokeWidth = 2.dp
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Menambahkan...",
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                } else {
                                    Text(
                                        text = "+ Tambah",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }

                            }
                        }
                    }
                }


            }

            if (isFullScreen) {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Kembali ke Katalog")
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    PrakTAM2_2417051059Theme {
        DashboardScreen()
    }
}
@Preview(showBackground = true)
@Composable
fun DaftarBajuPreview() {
    val navController = rememberNavController()
    PrakTAM2_2417051059Theme {
        DaftarBajuScreen(navController)
    }
}

