package com.example.praktam2_2417051059

import Model.SourcePakaian
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.praktam2_2417051059.ui.theme.PrakTAM2_2417051059Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrakTAM2_2417051059Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

//@Composable
//fun Main(){
//    Scaffold()
//}

@Composable
fun Greeting(modifier: Modifier) {
    Column(modifier = Modifier.fillMaxSize().padding(all = 30.dp)) {
        for (pakaian in SourcePakaian.jenisPakaian){
            Row {
                Image(
                    painter = painterResource(id = pakaian.ImageRes),
                    contentDescription = "Gambar",
                    modifier = Modifier.size(100.dp)
                )
                Spacer(Modifier.width(20.dp))
                Column() {
                    Text(text = "Nama: ${pakaian.nama}")
                    Text(text = "Harga: ${pakaian.harga}")
                }
            }
        Spacer(Modifier.height(20.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PrakTAM2_2417051059Theme {
        Greeting(modifier = Modifier)
    }
}