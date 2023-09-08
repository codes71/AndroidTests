package com.example.realtimefirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var userNameField : EditText
    private lateinit var ageField : EditText
    private lateinit var phNoField : EditText
    private lateinit var emailField : EditText
    private lateinit var buttonRegister : Button

//    private lateinit var database : FirebaseDatabase
    private lateinit var myRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userNameField = findViewById(R.id.userName)
        ageField = findViewById(R.id.Age)
        phNoField = findViewById(R.id.phNo)
        emailField = findViewById(R.id.Email)
        buttonRegister = findViewById(R.id.Regbutton)
//
//        database = FirebaseDatabase.getInstance()
        myRef = Firebase.database.reference
//
        buttonRegister.setOnClickListener{
            val name = userNameField.text.toString()
            val age = ageField.text.toString()
            val phNo = phNoField.text.toString()
            val email = emailField.text.toString()
            if (
                name.isNullOrBlank() or
                age.isNullOrBlank() or
                phNo.isNullOrBlank() or
                email.isNullOrBlank()
                )
            {
                Toast.makeText(
                    this,
                    "No EmptyVAl Allow!",
                    Toast.LENGTH_LONG).show()
//                return@setOnClickListener
            }
            val user = User(name,age,phNo,email)

            myRef.push().setValue(user)
                .addOnCompleteListener{
                    Toast.makeText(
                        this,
                        "Success!",
                        Toast.LENGTH_LONG)
                        .show()
                    clearTextField()
                }
                .addOnFailureListener{
                    Toast.makeText(
                        this,
                        "Failed",
                        Toast.LENGTH_LONG)
                        .show()
                }
        }

    }
    private fun clearTextField(){
        userNameField.text.clear()
        ageField.text.clear()
        phNoField.text.clear()
        emailField.text.clear()
    }
}


data class User(
    var name : String,
    var age : String,
    var phno : String,
    var email : String
)