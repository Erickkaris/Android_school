package com.example.schoolapp.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.schoolapp.models.UserModel
import com.example.schoolapp.navigation.ROUTE_DASHBOARD
import com.example.schoolapp.navigation.ROUTE_LOGIN
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel: ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signup(username: String,
               fullName: String,
               email: String,
               password: String,
               confirmPassword: String,
               navController: NavController,
               context: Context){
        if (username.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank() || fullName.isBlank()){
            Toast.makeText(context,"Please fill all the fields", Toast.LENGTH_LONG).show()
            return
        }
        if (password != confirmPassword){
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
        }
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            task ->
            if (task.isSuccessful){
                val userId = auth.currentUser?.uid ?: ""
                val user = UserModel(username=username, email=email,userId = userId, fullName = fullName)

                saveUserToDatabase(user, navController, context)
            }else{
                Toast.makeText(context,"Registration failed", Toast.LENGTH_LONG).show()
            }
        }

    }


    //This is a helper function that helps to create a table called 'User' and save the user to the database.
    private fun saveUserToDatabase(user: UserModel, navController: NavController, context: Context){
        val dbRef = FirebaseDatabase.getInstance().getReference("User/${user.userId}")
        dbRef.setValue(user).addOnCompleteListener {
            task ->
            if (task.isSuccessful){
                Toast.makeText(context, "User registered successfully", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_LOGIN){
                    popUpTo(0)
                }
            }else{
                Toast.makeText(context,"Failed to save user", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun login(email: String, password: String, navController: NavController, context: Context){
        if (email.isBlank() || password.isBlank()){
            Toast.makeText(context, "Email and password required", Toast.LENGTH_SHORT).show()
        }
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            task ->
            if (task.isSuccessful){
                Toast.makeText(context,"Login successful", Toast.LENGTH_LONG).show()
                navController.navigate(route = ROUTE_DASHBOARD){
                    popUpTo(0)
                }
            }else{
                Toast.makeText(context,"Login failed", Toast.LENGTH_LONG).show()
            }
        }

    }
}