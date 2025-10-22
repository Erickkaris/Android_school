package com.example.schoolapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.schoolapp.ui.theme.screens.SplashScreen
import com.example.schoolapp.ui.theme.screens.dashboard.dashboardScreen
import com.example.schoolapp.ui.theme.screens.employees.EmployeeListScreen
import com.example.schoolapp.ui.theme.screens.employees.addEmployeeScreen
import com.example.schoolapp.ui.theme.screens.employees.updateEmployeeScreen
import com.example.schoolapp.ui.theme.screens.login.loginScreen
import com.example.schoolapp.ui.theme.screens.register.registerScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), startDestination: String = ROUTE_SPLASHSCREEN){
    NavHost(navController = navController, startDestination = startDestination){
        composable (route = ROUTE_REGISTER){ registerScreen(navController) }
        composable (route = ROUTE_LOGIN){ loginScreen(navController) }
        composable (route = ROUTE_DASHBOARD){ dashboardScreen(navController) }
        composable (route = ROUTE_ADD_EMPLOYEE){ addEmployeeScreen(navController) }
        composable (route = ROUTE_VIEW_EMPLOYEE){ EmployeeListScreen(navController) }
        composable (route = ROUTE_SPLASHSCREEN){ SplashScreen { navController.navigate(ROUTE_LOGIN)
        {popUpTo(ROUTE_SPLASHSCREEN) { inclusive=true } } } }
        composable(route = ROUTE_UPDATE_EMPLOYEE,
            arguments = listOf(
                navArgument("employeeId")
                {type = NavType.StringType}
            )){
            backStackEntry ->
            val employeeId = backStackEntry.arguments?.getString("employeeId")!!
            updateEmployeeScreen(navController, employeeId)
        }
    }
}