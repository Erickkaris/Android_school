package com.example.schoolapp.ui.theme.screens.login

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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.schoolapp.R
import com.example.schoolapp.navigation.ROUTE_REGISTER

@Composable
fun loginScreen(navController: NavController){

    var username by remember  { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column (modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Text(text = "Login Here", fontWeight = FontWeight.Bold, fontSize = 36.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Image(painter = painterResource(R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(140.dp))

        Spacer(modifier = Modifier.height(20.dp))


        OutlinedTextField(value = email,
            onValueChange = {email = it},
            leadingIcon = { Icon(Icons.Default.Email,
                contentDescription = null) },
            label = { Text("Enter Email") },
            placeholder = { Text("Please enter your email") })

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(value = password,
            onValueChange = {password = it},
            leadingIcon = { Icon(Icons.Default.Lock,
                contentDescription = null) },
            label = { Text("Enter Password") },
            placeholder = { Text("Please enter your password") },
            visualTransformation = PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(24.dp))


        Button(onClick = {}, modifier = Modifier.fillMaxWidth()
            .height(50.dp) ) {
            Text("LOGIN", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text("Don't have an account?")

        Text("Register here",
            fontWeight = FontWeight.Bold,
            color = Color.Blue,
            modifier = Modifier.clickable{navController.navigate(ROUTE_REGISTER)}
        )
    }
}


@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun LoginScreenPreview(){
    loginScreen(navController = rememberNavController())
}