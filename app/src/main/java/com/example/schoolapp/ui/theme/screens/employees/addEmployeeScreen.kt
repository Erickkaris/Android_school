package com.example.schoolapp.ui.theme.screens.employees

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.schoolapp.R
import com.example.schoolapp.navigation.ROUTE_VIEW_EMPLOYEE
import com.example.schoolapp.viewmodels.EmployeeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addEmployeeScreen(navController: NavController) {

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var salary by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("Select gender") }
    var nationality by remember { mutableStateOf("") }
    var summary by remember { mutableStateOf("") }


    val genderOptions = listOf("Select Gender", "Male", "Female", "Other")
    var expanded by remember { mutableStateOf(false) }

    val imageUri = rememberSaveable { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent())
    { uri: Uri? -> uri?.let { imageUri.value = it } }

    val context = LocalContext.current
    val employeeViewModel: EmployeeViewModel = viewModel()


    Card (modifier = Modifier.padding(24.dp ),
        elevation = CardDefaults.cardElevation(6.dp)){

        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp).verticalScroll(rememberScrollState(0 )),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "Add New Employee",
                fontSize = 24.sp,
                color = Color(0xFF004D40)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(6.dp),
                modifier = Modifier.size(140.dp)
                    .clickable { launcher.launch("image/*") }
            ) {
                AnimatedContent(
                    targetState = imageUri.value,
                    label = ""
                ) { targetUri ->
                    AsyncImage(
                        model = targetUri ?: R.drawable.ic_person,
                        contentDescription = "Employee Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Tap to upload picture",
                color = Color.Black
            )

            Divider(
                modifier = Modifier.padding(vertical = 20.dp),
                color = Color.Black,
                thickness = 1.dp
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Enter your Name") },
                placeholder = { Text("Enter your name") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Enter your Age") },
                placeholder = { Text("Enter your Age") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Face,
                        contentDescription = null
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = salary,
                onValueChange = { salary = it },
                label = { Text("Enter your salary") },
                placeholder = { Text("Enter your salary") },
                leadingIcon = {
                    Icon(painter = painterResource(R.drawable.outline_attach_money_24),
                        contentDescription = null)
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(10.dp))


            var countryexpanded by remember { mutableStateOf(false) }
            val countries = listOf("Kenya", "Uganda", "Tanzania", "Rwanda", "Burundi", "Ethiopia")

            ExposedDropdownMenuBox(
                expanded = countryexpanded,
                onExpandedChange = {countryexpanded = !countryexpanded},
                modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
            ) {
                OutlinedTextField(
                    value = nationality,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Select your Nationality") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.outline_flag_24),
                            contentDescription = null
                        )
                    },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(countryexpanded)},
                    modifier = Modifier.fillMaxWidth().menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = countryexpanded,
                    onDismissRequest = { countryexpanded = false}
                ) {
                    countries.forEach {
                        selectedCountry ->
                        DropdownMenuItem(
                            text = {Text(selectedCountry)},
                            onClick = {
                                nationality = selectedCountry
                                countryexpanded = false
                            }
                        )

                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box() {
                OutlinedTextField(
                    value = selectedGender,
                    onValueChange = { },
                    readOnly = true,
                    placeholder = { Text("Enter your gender") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.outline_man_24),
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { expanded = true}) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "Select Gender")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {expanded = false}
                ) {
                    genderOptions.forEach { gender ->
                        DropdownMenuItem(
                            text = { Text(gender) },
                            onClick = {
                            selectedGender = gender
                            expanded = false })
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))



            OutlinedTextField(
                value = summary,
                onValueChange = { summary = it },
                label = { Text("Details Summary") },
                placeholder = { Text("Enter your details summary") },
                // leadingIcon = { Icon(painter = painterResource(R.drawable.outline_summarize_24),
                //    contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
                    .height(120.dp),
                maxLines = 5
            )
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier.width(140.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                )
                {
                    Text("CANCEL", color = Color.Black)
                }

                Button(
                    onClick = { employeeViewModel.uploadEmployee(
                        imageUri.value,
                        name,
                        age,
                        salary,
                        selectedGender,
                        nationality,
                        summary,
                        context,
                        navController,
                       // navController.navigate(ROUTE_VIEWEMPLOYEE)
                              )
                    },
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
}

//@Composable
//fun SelectGenderDropdownMenu(){
//    var expanded by remember { mutableStateOf(false) }
//
//
//}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun addEmployeeScreenPreview(){
    addEmployeeScreen(navController = rememberNavController())
}