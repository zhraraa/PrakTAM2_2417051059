package com.example.praktam2_2417051059

import com.example.praktam2_2417051059.data.Model.Pakaian
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.compose.material3.Scaffold
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.TextButton
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.praktam2_2417051059.data.network.RetrofitClient
import com.example.praktam2_2417051059.data.repository.PakaianRepository


import com.example.praktam2_2417051059.ui.theme.PrakTAM2_2417051059Theme
import component.pages.DaftarBajuScreen
import component.pages.DaftarPelanggan
import component.pages.DashboardScreen
import component.pages.DetailBaju

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
fun AppNavigation(navController: NavHostController) {
    var pakaianList by remember { mutableStateOf<List<Pakaian>>(emptyList()) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            NavHost(
                navController = navController,
                startDestination = "dashboardScreen"
            ){
                composable("dashboardScreen"){
                    DashboardScreen(navController = navController)
                }

                composable("daftarPelanggan"){
                    DaftarPelanggan(navController = navController)
                }

                composable("daftarBaju"){
                    DaftarBajuScreen(navController = navController, namaPelanggan = null) { fetchedPakaian ->
                        pakaianList = fetchedPakaian
                    }
                }

                composable("katalog/{namaPelanggan}"){ backStackEntry ->
                    val namaPelanggan = backStackEntry.arguments?.getString("namaPelanggan")
                    DaftarBajuScreen(navController = navController, namaPelanggan = namaPelanggan) { fetchedPakaian ->
                        pakaianList = fetchedPakaian
                    }
                }

                composable("detail/{namaPelanggan}/{namaBaju}") { backStackEntry ->
                    val namaPelanggan = backStackEntry.arguments?.getString("namaPelanggan")
                    val namaBaju = backStackEntry.arguments?.getString("namaBaju")

                    val pakaian = pakaianList.find {
                        it.nama == namaBaju
                    }

                    if (pakaian != null) {
                        DetailBaju(pakaian = pakaian, namaPelanggan = namaPelanggan, navController = navController)
                    }
                }
            }
        }

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val halamanSaatIni = navBackStackEntry?.destination?.route

        // sembunyiin Navbar kalau rutenya berawalan "detail"
        if (halamanSaatIni?.startsWith("detail") != true) {
            Navbar(navController)
        }
    }
}

@Composable
fun Navbar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val halamanSaatIni = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = Modifier
            .border(width = 2.dp, color = Color(0xFFe2e8f0))
            .background(Color.White)
            .padding(15.dp, 0.dp),
        containerColor = Color.White
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home", modifier = Modifier.size(30.dp)) },
            label = { Text(text = "Beranda") },
            selected = halamanSaatIni == "dashboardScreen",
            onClick = {
                navController.navigate("dashboardScreen") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },

            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                indicatorColor = Color.Transparent,

                unselectedTextColor = MaterialTheme.colorScheme.secondary,
                unselectedIconColor = MaterialTheme.colorScheme.secondary
            )
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "Katalog", modifier = Modifier.size(30.dp)) },
            label = { Text(text = "Katalog") },
            selected = halamanSaatIni == "daftarBaju",
            onClick = {
                navController.navigate("daftarBaju") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },

            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                indicatorColor = Color.Transparent,

                unselectedTextColor = MaterialTheme.colorScheme.secondary,
                unselectedIconColor = MaterialTheme.colorScheme.secondary
            )
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Menu, contentDescription = "Pesanan", modifier = Modifier.size(30.dp)) },
            label = { Text(text = "Pesanan") },
            selected = halamanSaatIni == "daftarPesanan",
            onClick = {
                navController.navigate("daftarPesanan") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },

            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                indicatorColor = Color.Transparent,

                unselectedTextColor = MaterialTheme.colorScheme.secondary,
                unselectedIconColor = MaterialTheme.colorScheme.secondary
            )
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Pelanggan", modifier = Modifier.size(30.dp)) },
            label = { Text(text = "Pelanggan") },
            selected = halamanSaatIni == "daftarPelanggan",
            onClick = {
                navController.navigate("daftarPelanggan") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },

            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                indicatorColor = Color.Transparent,

                unselectedTextColor = MaterialTheme.colorScheme.secondary,
                unselectedIconColor = MaterialTheme.colorScheme.secondary
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SewNotePreview() {
    val navController = rememberNavController()
    PrakTAM2_2417051059Theme {
        DaftarBajuScreen(navController)
    }
}

