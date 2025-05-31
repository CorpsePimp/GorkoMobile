package com.example.gorko.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RegistrationScreen(
    navController: NavController? = null,
    onRegisterClick: (() -> Unit)? = null,
    onLoginClick: (() -> Unit)? = null
) {
    var name by remember { mutableStateOf("") }
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDF6F7))
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(40.dp))
        // Круглый лейбл
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color(0xFFF8D9DE)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Горько!",
                style = TextStyle(
                    color = Color(0xFFC58C94),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(Modifier.height(36.dp))
        // Поля ввода
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Имя", color = Color(0xFFC58C94)) },
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFF8D9DE),
                focusedBorderColor = Color(0xFFC58C94),
                cursorColor = Color(0xFFC58C94),
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            ),
            textStyle = TextStyle(color = Color(0xFFC58C94))
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = login,
            onValueChange = { login = it },
            placeholder = { Text("Логин", color = Color(0xFFC58C94)) },
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFF8D9DE),
                focusedBorderColor = Color(0xFFC58C94),
                cursorColor = Color(0xFFC58C94),
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            ),
            textStyle = TextStyle(color = Color(0xFFC58C94))
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Пароль", color = Color(0xFFC58C94)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFF8D9DE),
                focusedBorderColor = Color(0xFFC58C94),
                cursorColor = Color(0xFFC58C94),
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            ),
            textStyle = TextStyle(color = Color(0xFFC58C94))
        )
        Spacer(Modifier.height(28.dp))
        // Кнопка регистрации
        Button(
            onClick = { onRegisterClick?.invoke() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF8D9DE),
                contentColor = Color(0xFFC58C94)
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Text(
                text = "Регистрация",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(Modifier.height(24.dp))
        // Ссылка "Войти"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Уже есть аккаунт? ",
                color = Color(0xFFC58C94),
                fontSize = 15.sp
            )
            Text(
                text = "Войти",
                color = Color(0xFFC58C94),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable { onLoginClick?.invoke() }
                    .padding(bottom = 2.dp)
            )
        }
    }
}