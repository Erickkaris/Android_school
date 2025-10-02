package com.example.schoolapp.ui.theme.screens.employees

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.schoolapp.R


@Composable
fun addEmployeeScreen(navController: NavController){

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var nationality by remember { mutableStateOf("") }
    var summary by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    val imageUri = rememberSaveable { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent())
    { uri: Uri? -> uri?.let { imageUri.value = it }}

    Column (modifier = Modifier.fillMaxWidth()
        .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally){

        Text("Add New Employee",
            fontSize = 24.sp,
            color = Color(0xFF004D40))

        Spacer(modifier = Modifier.height(16.dp))

        Card (shape = CircleShape,
            elevation = CardDefaults.cardElevation( 6.dp),
            modifier = Modifier.size(140.dp)
                .clickable{launcher.launch("image/*")}
        ){
            AnimatedContent(targetState = imageUri.value,
                label = "") { targetUri ->
                AsyncImage(
                    model = targetUri ?: R.drawable.ic_person,
                    contentDescription = "Employee Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text("Tap to upload picture",
            color = Color.Black)

        Divider(
            modifier = Modifier.padding(vertical = 20.dp),
            color = Color.Black,
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = name,
            onValueChange = {name = it},
            label = {Text("Enter your Name")},
            placeholder = {Text("Enter your name")},
            leadingIcon = { Icon(Icons.Default.Person,
                contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = age,
            onValueChange = {age = it},
            label = {Text("Enter your Age")},
            placeholder = {Text("Enter your Age")},
            leadingIcon = { Icon(Icons.Default.Face,
                contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = nationality,
            onValueChange = {nationality = it},
            label = {Text("Enter your Nationality")},
            placeholder = {Text("Enter your nationality")},
            leadingIcon = { Icon(painter = painterResource(R.drawable.outline_flag_24),
                contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = gender,
            onValueChange = {gender = it},
            label = {Text("Enter your Gender")},
            placeholder = {Text("Enter your gender")},
            leadingIcon = { Icon(painter = painterResource(R.drawable.outline_man_24),
                contentDescription = null) },
//            trailingIcon = {Icon(painter = painterResource(R.drawable.outline_woman_24),
//                contentDescription = null)},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))



        TextField(
            value = summary,
            onValueChange = {summary = it},
            label = {Text("Details Summary")},
            placeholder = {Text("Enter your details summary")},
           // leadingIcon = { Icon(painter = painterResource(R.drawable.outline_summarize_24),
            //    contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
                .height(120.dp),
            maxLines = 5
        )
        Spacer(modifier = Modifier.height(20.dp))

        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly){
            Button(onClick = {},
                modifier = Modifier.width(140.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            )
            {
                Text("CANCEL", color = Color.Black)
            }

            Button(onClick = {},
                modifier = Modifier.width(140.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
            )
            {
                Text("SAVE", color = Color.Black)
            }
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun addEmployeeScreenPreview(){
    addEmployeeScreen(navController = rememberNavController())
}