package com.example.praktam2_2417051059

import Model.Pakaian
import Model.SourcePakaian
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
                DaftarBajuScreen()
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFF1F1F1))
                .padding(horizontal = 20.dp)
        ) {
            SourcePakaian.jenisPakaian.forEach { pakaian ->
                DetailScreen(pakaian = pakaian)
                Spacer(modifier = Modifier.height(35.dp))
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
            border = BorderStroke(1.dp, Color(0xFFE8E8E8))
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
fun DaftarBajuPreview() {
    PrakTAM2_2417051059Theme {
        DaftarBajuScreen()
    }
}