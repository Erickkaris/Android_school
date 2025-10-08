package com.example.schoolapp.models

data class EmployeeModel(
    var id: String? = null,
    var name: String? = null,
    var age: String? = null,
    val salary: String? = null,
    var gender: String? = null,
    var nationality: String? = null,
    var summary: String? = null,
    var imageUrl: String? = null
)
