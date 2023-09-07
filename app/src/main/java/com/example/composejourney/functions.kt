package com.example.composejourney

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composejourney.screens.Login
import com.example.composejourney.screens.SignUp
import com.example.composejourney.screens.welcomeScreen
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

fun showToast(context : Context, text: String){
    val toasting = Toast.makeText(context,text,Toast.LENGTH_LONG)
    toasting.show()
}

@Composable
fun navHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    auth: FirebaseAuth,
    activity: MainActivity
){
    NavHost(
        navController = navController,
        startDestination = Destinations.LogIn.route,
        modifier = modifier
    ){
        composable( route = Destinations.LogIn.route) {
            Login(auth = auth, activity = activity,navController)
        }
        composable(route = Destinations.Welcome.route){
            welcomeScreen()
        }
        composable(route = Destinations.SignUp.route){
            SignUp(
                navController = navController,
                auth = auth,
                activity = activity
            )
        }
    }
}
sealed class Destinations(
    val route : String
){
    object LogIn : Destinations("LogIn")
    object Welcome : Destinations("welcome")
    object SignUp : Destinations("sign up")
}