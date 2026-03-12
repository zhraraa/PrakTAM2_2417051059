package com.example.praktam2_2417051059

import Model.Pakaian
import Model.SourcePakaian
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
            .verticalScroll(rememberScrollState())
            .padding(all = 30.dp)
            .background(Color(0xFFF1F1F1))

    ) {
        SourcePakaian.jenisPakaian.forEach { pakaian ->
            DetailScreen(pakaian = pakaian)
            Spacer(modifier = Modifier.height(35.dp))
        }
    }
}

@Composable
fun DetailScreen(pakaian: Pakaian){
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
                Image(
                    painter = painterResource(id = pakaian.ImageRes),
                    contentDescription = "Gambar",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.width(20.dp))
                Column() {
                    Text(
                        text = "${pakaian.nama}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(text = "${pakaian.harga}")
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = Color.White
                    )
                }
                Button(
                    onClick = {},
                    modifier = Modifier
                ) {
                    Text(
                        text = "+ Tambah",
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )

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