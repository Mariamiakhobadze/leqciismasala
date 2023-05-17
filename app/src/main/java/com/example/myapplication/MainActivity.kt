package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var registrationEmailEt : EditText
    private lateinit var registrationPasswordET : EditText
    private lateinit var registrationBtn : Button

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        listeners()

    }
    private fun listeners(){
        registrationBtn.setOnClickListener{
            val email = registrationEmailEt.text.toString()
            val password = registrationPasswordET.text.toString()

            if (email.isEmpty() || password.isEmpty() ||
                        !email.contains('@') || password.length < 5)
                return@setOnClickListener

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    Toast.makeText(this,"succesfully registered", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun init(){
        registrationEmailEt = findViewById(R.id.registrationEmailET)
        registrationPasswordET = findViewById(R.id.registrationPasswordET)
        registrationBtn = findViewById(R.id.button)
    }
}