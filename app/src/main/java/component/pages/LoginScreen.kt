package component.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.praktam2_2417051059.R
import com.example.praktam2_2417051059.data.Model.SourceUser

@Composable
fun LoginScreen(navController: NavController) {
    // State untuk inputan
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Background utama putih bersih
    ) {
        // --- BAGIAN COKELAT ATAS (HEADER CURVE) ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f) // Mengambil 35% tinggi layar
                .clip(RoundedCornerShape(bottomStart = 80.dp, bottomEnd = 80.dp)) // Efek lengkungan
                .background(MaterialTheme.colorScheme.onPrimary), // Cokelat tua polos mewah
            contentAlignment = Alignment.Center
        ) {
            // Lingkaran Logo "Z"
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                    contentDescription = "Logo",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        // --- BAGIAN FORM LOGIN ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5C4033),
                fontFamily = FontFamily.Serif
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (loginError) {
                Text(
                    text = "Email atau Password salah!",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Field Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                placeholder = { Text("nama@email.com") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF9F9F9),
                    focusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Field Password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                placeholder = { Text("••••••••") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible) painterResource(id = R.drawable.ic_visibility) else painterResource(id = R.drawable.ic_visibility_off)
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(painter = image, contentDescription = null)
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF9F9F9),
                    focusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Tombol Login
            Button(
                onClick = {
                    if (email == SourceUser.user.email && password == SourceUser.user.password) {
                        navController.navigate("dashboardScreen") {
                            popUpTo("login") { inclusive = true }
                        }
                    } else {
                        loginError = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF333333)) // Hitam/Cokelat Sangat Tua
            ) {
                Text("Login", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}