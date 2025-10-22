package com.example.schoolapp.ui.theme.screens.employees

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.schoolapp.models.EmployeeModel
import com.example.schoolapp.viewmodels.EmployeeViewModel

@Composable
fun EmployeeListScreen(navController: NavController){
    val employeeViewModel: EmployeeViewModel = viewModel()
    val context = LocalContext.current
    val employees = employeeViewModel.employees
    LaunchedEffect(Unit) {
        employeeViewModel.fetchEmployee(context)
    }

    LazyColumn (
        modifier = Modifier.fillMaxSize()
            .background(Color.LightGray),
        contentPadding = PaddingValues(vertical = 50.dp,
            horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(employees){employee ->
            EmployeeCard(
                employee = employee,

                onDelete = { employeeId ->
                    employeeViewModel.deleteEmployee(employeeId, context) },

                navController = navController
            )
        }
    }
}

@Composable
fun EmployeeCard(employee: EmployeeModel,
                 onDelete: (String) -> Unit,
                 navController: NavController
){

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog){
        AlertDialog(
            onDismissRequest = {showDialog = false},
            title = {Text("Confirm delete")},
            text = {Text("Are you sure you want to delete this employee?")},

            confirmButton = { TextButton(
                onClick = {showDialog = false
                           employee.id?.let { onDelete(it) }
                })
            { Text("Yes")}},

            dismissButton = {TextButton(onClick = {
                showDialog = false
            })
            {Text("Cancel") }}
        )
    }

    Card (
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .shadow(elevation = 6.dp,
                shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(6.dp)
    ){
        Column (modifier = Modifier.padding(16.dp)){
            Row (verticalAlignment = Alignment.CenterVertically){
                employee.imageUrl?.let { imageUrl ->
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Employee Image",
                        modifier = Modifier.size(64.dp)
                            .clip(CircleShape)
                            .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))

                Column (modifier = Modifier.weight(1f)){
                    Text(
                        text = employee.name ?: "No name",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "Age: ${employee.age ?: "N/A"}",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = "Diagnosis: ${employee.summary ?: "N/A"}",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ){

                        TextButton(onClick = {
                            navController.navigate("update_employee/${employee.id}")
                        }) {
                            Text(
                                text = "Update",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                          TextButton(
                              onClick = { showDialog = true }
                          ) {
                            Text(
                                text = "Delete",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.error
                            )
                        }


                    }
                }
            }
        }
    }
}