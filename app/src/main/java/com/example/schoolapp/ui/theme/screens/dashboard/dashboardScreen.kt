package com.example.schoolapp.ui.theme.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dashboardScreen(){
    
    val selectedItem = remember { mutableStateOf(0) }
    
    Scaffold (
        topBar = {
            TopAppBar(title = { Text("Nairobi County", fontSize = 24.sp) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF004D40),
                    titleContentColor = Color.White))
        },
        bottomBar = {
            NavigationBar (containerColor = Color(0xFF004D40)){

                NavigationBarItem(
                    selected = selectedItem.value == 0,
                    onClick = {selectedItem.value = 0},
                    icon = { Icon(Icons.Filled.Phone, contentDescription = "call") },
                    label = { Text("Call")})

                NavigationBarItem(
                    selected = selectedItem.value == 1,
                    onClick = {selectedItem.value = 1},
                    icon = {Icon(Icons.Filled.Email, contentDescription = "Email")},
                    label = {Text("Email")}
                )

                NavigationBarItem(
                    selected = selectedItem.value == 2,
                    onClick = {selectedItem.value = 2},
                    icon = {Icon(Icons.Filled.Share, contentDescription = "Share")},
                    label = {Text("Share")}
                )
            }
        }
    ){ innerPadding ->
        Column (
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ){
            Text("Welcome to Nairobi County App",
                fontSize = 24.sp,
                color = Color(0xFf004D40)
            )

            Row(modifier = Modifier.fillMaxWidth()
                .padding(bottom = 16.dp, top = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly) {

                Card(modifier = Modifier.size(100.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0XFFE0F2F1)),
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("200", fontSize = 20.sp, color = Color(0xFF004D40))
                        Text("Employees", fontSize = 14.sp, color = Color.Gray)
                    }
                }

                Card(modifier = Modifier.size(100.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0XFFE0F2F1)),
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("14", fontSize = 20.sp, color = Color(0xFF004D40))
                        Text("Subcounties", fontSize = 14.sp, color = Color.Gray)
                    }
                }

                Card(modifier = Modifier.size(100.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0XFFE0F2F1)),
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("8", fontSize = 20.sp, color = Color(0xFF004D40))
                        Text("Wards", fontSize = 14.sp, color = Color.Gray)
                    }
                }

            }
        }

    }

}


@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun dashboardScreenPreview(){
    dashboardScreen()
}