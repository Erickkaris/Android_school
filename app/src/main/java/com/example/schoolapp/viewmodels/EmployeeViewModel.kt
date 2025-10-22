package com.example.schoolapp.viewmodels

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.schoolapp.models.EmployeeModel
import com.example.schoolapp.navigation.ROUTE_VIEW_EMPLOYEE
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.InputStream

class EmployeeViewModel : ViewModel() {

        val cloudinaryUrl = "https://api.cloudinary.com/v1_1/dmd33qk8e/image/upload"
        val uploadPreset = "employee_img"

    fun uploadEmployee(imageUri: Uri?,
                       name: String,
                       age: String,
                       salary: String,
                       gender: String,
                       nationality:String,
                       summary: String,
                       context: Context,
                       navController: NavController,
//                       navigate: Unit
){
        viewModelScope.launch (Dispatchers.IO){
            try {
                val imageUrl = imageUri?.let { uploadToCloudinary(context,it) }

                //Create a database table named 'employee'
                val ref = FirebaseDatabase.getInstance().getReference("Employees").push()
                val employeeData = mapOf(
                    "id" to ref.key,
                    "name" to name,
                    "age" to age,
                    "salary" to salary,
                    "gender" to gender,
                    "nationality" to nationality,
                    "summary" to summary,
                    "imageUrl" to imageUrl
                )
                ref.setValue(employeeData).await()
                withContext(Dispatchers.Main){
                    Toast.makeText(context, "Employee saved Successfully", Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_VIEW_EMPLOYEE)
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(context, "Employee not saved", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun uploadToCloudinary(context: Context, uri: Uri): String{
        val contentResolver = context.contentResolver
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        val fileBytes = inputStream?.readBytes() ?: throw Exception("Image read failed")
        val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("file", "image.jpg",
                RequestBody.create("image/*".toMediaTypeOrNull(),fileBytes))
            .addFormDataPart("upload_preset", uploadPreset).build()
        val request = Request.Builder().url(cloudinaryUrl).post(requestBody).build()
        val response = OkHttpClient().newCall(request).execute()
        if (!response.isSuccessful) throw Exception("Upload failed")
        val responseBody = response.body?.string()
        val secureUrl = Regex("\"secure_url\":\"(.*?)\"").find(responseBody ?: "")?.groupValues?.get(1)
        return secureUrl ?: throw Exception("Failed to get image")
    }


    private val _employees = mutableStateListOf<EmployeeModel>()
    val employees:List<EmployeeModel> = _employees
    fun fetchEmployee(context: Context) {
        val ref = FirebaseDatabase.getInstance().getReference("Employees")
        ref.get().addOnSuccessListener { snapshot ->
            _employees.clear()
            for (child in snapshot.children){
                val employee = child.getValue(EmployeeModel::class.java)
                employee?.let {
                    it.id = child.key
                    _employees.add(it)
                }
            }
        }.addOnFailureListener {
            Toast.makeText(context, "Failed to retrieve employees", Toast.LENGTH_LONG).show()
        }

    }
    fun deleteEmployee(employeeId: String, context: Context){
        val ref = FirebaseDatabase.getInstance().getReference("Employees").child(employeeId)
        ref.removeValue().addOnSuccessListener {
            _employees.removeAll{it.id == employeeId}
        }.addOnFailureListener {
            Toast.makeText(context,"Employee not deleted", Toast.LENGTH_SHORT).show()
        }

    }
    fun updateEmployee(
        employeeId: String,
        imageUri: Uri?,
        name: String,
        gender: String,
        nationality: String,
        age: String,
        summary: String,
        context: Context,
        navController: NavController
    ){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val imageurl = imageUri?.let { uploadToCloudinary(context,it) }
                val updateEmployee = mapOf(
                    "id" to employeeId,
                    "name" to name,
                    "gender" to gender  ,
                    "nationality" to nationality,
                    "age" to age,
                    "diagnosis" to summary,
                    "imageUrl" to imageurl
                )
                val ref = FirebaseDatabase.getInstance().getReference("Employees").child(employeeId)
                ref.updateChildren(updateEmployee).await()
                fetchEmployee(context)
                withContext(Dispatchers.Main){
                    Toast.makeText(context,"Employee updated successfully", Toast.LENGTH_SHORT).show()
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(context,"Update failed", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}