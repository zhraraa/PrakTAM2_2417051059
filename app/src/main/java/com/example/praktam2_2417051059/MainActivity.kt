package com.example.praktam2_2417051059

import Model.Pakaian
import Model.Pemesanan
import Model.SourcePakaian
import Model.SourcePemesanan
import android.R.attr.icon
import android.R.attr.onClick
import android.R.id.icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praktam2_2417051059.ui.theme.PrakTAM2_2417051059Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrakTAM2_2417051059Theme {
                DashboardScreen()
            }
        }
    }
}

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F1F1))
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
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFCFAA88)
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
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE9EDCA))
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
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF929C71)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Pesanan\nAktif",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 15.sp
                    )
                }
            }
            Card(
                modifier = Modifier
                    .weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEDE1D5))
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
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFFB5875F)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Pesanan\nSelesai",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 15.sp
                    )
                }
            }
            Card(
                modifier = Modifier
                    .weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F1F1)),
                border = BorderStroke(1.dp, Color(0xFF000000))
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "85"
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Total\nPelanggan",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 15.sp
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
                        tint = Color(0xFFB5875F)
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
                        tint = Color(0xFFB5875F)
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
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F1F1)),
        border = BorderStroke(width = 1.dp, Color.Black)

    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .padding(vertical = 10.dp)
                .width(170.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(
                text = "2 hari lagi",
                color = Color(0xFFB5875F),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0xFFEDE1D5))
                    .padding(horizontal = 7.dp)

            )
            Text(
                text = "Dress Polos",
                fontWeight = FontWeight.Bold
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
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE9EDCA)),

    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .padding(vertical = 10.dp)
                .width(170.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(
                text = "${pemesanan.nama}",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${pemesanan.tanggal}",
                fontSize = 14.sp
           )

            Row(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color(0xFFF1F1F1))
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${pemesanan.jenisPakaian}",
                    fontSize = 14.sp
                )

                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(25.dp),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE9EDCA)
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
fun DaftarBajuScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F1F1))
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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF1F1F1)),
            contentPadding = PaddingValues(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(SourcePakaian.jenisPakaian) { pakaian ->
                DetailScreen(pakaian = pakaian)
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
            tint = Color(0xFFD4A373),
            modifier = Modifier
                .size(23.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Katalog & Layanan",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD4A373)
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
                    tint = Color(0xFFD4A373),
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    }
}

@Composable
fun DetailScreen(pakaian: Pakaian){
    var isFavorite by remember { mutableStateOf(false) }
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
                    Image(
                        painter = painterResource(id = pakaian.ImageRes),
                        contentDescription = "Gambar",
                        modifier = Modifier
                            .size(110.dp)
                            .clip(RoundedCornerShape(10.dp)),
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
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
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
                                containerColor = Color(0xFFEDE0D4)
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit",
                                tint = Color(0xFFD4A373),
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        Button(
                            onClick = {},
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFEDE0D4)
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "+ Tambah",
                                fontSize = 12.sp,
                                color = Color(0xFFD4A373)
                            )

                        }
                    }
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
    PrakTAM2_2417051059Theme {
        DaftarBajuScreen()
    }
}

