package com.example.schoolapp.ui.theme.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.schoolapp.R
import com.example.schoolapp.navigation.ROUTE_DASHBOARD
import com.example.schoolapp.navigation.ROUTE_LOGIN

@Composable
fun registerScreen(navController: NavController){

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column (  modifier = Modifier.fillMaxSize()
        .padding(horizontal = 24.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Text(
            text = "Register Here",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App logo",
            modifier = Modifier.size(140.dp)
        )

        OutlinedTextField(value = username,
            onValueChange = {username = it},
            leadingIcon = {Icon(Icons.Default.Person, contentDescription = null)},
            label = { Text(text = "Enter Username")},
            placeholder = {Text("Please enter username")})
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(value = email,
            onValueChange = {email = it},
            leadingIcon = {Icon(Icons.Default.Email, contentDescription = null)},
            label = {Text("Enter email")},
            placeholder = {Text("Please enter email.")}
            )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(value = password,
            onValueChange = {password = it},
            label = {Text("Enter password")},
            leadingIcon = {Icon(Icons.Default.Lock, contentDescription = null)},
            placeholder = {Text("Please enter password.")},
            visualTransformation = PasswordVisualTransformation()
            )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(value = confirmPassword,
            onValueChange = {confirmPassword = it},
            leadingIcon = {Icon(Icons.Default.Lock, contentDescription = null)},
            label = {Text("Confirm password")},
            placeholder = {Text("Please confirm your password.")},
            visualTransformation = PasswordVisualTransformation()
            )
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {},
            modifier = Modifier.fillMaxWidth()
                .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) { Text(text = "REGISTER",
            color = Color.White,
            fontSize = 20.sp)}

        Text(text = "Already regestered?")
        Text("Login here",
            modifier = Modifier.clickable{navController.navigate(ROUTE_DASHBOARD)},
            color = Color.Blue,
            fontWeight = FontWeight.Bold)
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun registerScreenPreview(){
    registerScreen( navController = rememberNavController())
}