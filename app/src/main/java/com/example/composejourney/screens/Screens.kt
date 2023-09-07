package com.example.composejourney.screens
import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.composejourney.MainActivity
import com.example.composejourney.showToast
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(
    navController: NavHostController,
    auth : FirebaseAuth,
    activity: Activity
) {
    var emailField by remember {
        mutableStateOf("")
    }
    var passwordField by remember {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize(1f)
            .padding(16.dp)
    )
    {
        Text(
            text = "Sign Up User",
            fontSize = 30.sp
        )

        OutlinedTextField(
            value = emailField,
            onValueChange = { emailField = it },
            label = {
                Text(text = "Enter Email")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = passwordField,
            onValueChange = { passwordField = it },
            label = {
                Text(text = "Enter Passsword")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        Button(
            onClick = {
               
            }) {
            Text(text = "SignUp")
        }
        Button(
            onClick = {
                navController.navigate("Login")
            }) {
            Text(text = "Log In")
        }
    }
}

@Composable
fun welcomeScreen(){
    Text(text = "Welcome ", fontSize = 30.sp)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    auth: FirebaseAuth,
    activity: Activity,
    navController : NavHostController
) {
    var emailField by remember {
        mutableStateOf("")
    }
    var passwordField by remember {
        mutableStateOf("")
    }
    val myContext = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize(1f)
            .padding(16.dp)

    )
    {
        OutlinedTextField(
            value = emailField,
            onValueChange = { emailField = it },
            label = {
                Text(text = "Enter Email")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = passwordField,
            onValueChange = { passwordField = it },
            label = {
                Text(text = "Enter Passsword")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        Button(
            onClick = {
                if (emailField.isNullOrBlank()){
                    showToast(myContext,"Enter null no all")
                    return@Button
                }
                auth.signInWithEmailAndPassword(emailField, passwordField)
                    .addOnCompleteListener(activity) { task ->
                        if (task.isSuccessful){
                            Log.i("gg", task.isSuccessful.toString())
                            navController.navigate("welcome")
                        }else {
                            Log.i("gg", task.isCanceled.toString())
                        }
                    }
            }) {
            Text(text = "LogIn")
        }
        Button(onClick = {
            navController.navigate("sign up")
        }) {
            Text(text = "Sign Up?")
        }
    }
}