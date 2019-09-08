package com.example.kotlinmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)

        register_button.setOnClickListener {
            performRegister()
        }

        already_have_account_textview.setOnClickListener {
//            Log.d("MainActivity", "Already have an account")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun performRegister() {
        val email = email_edittext_register.text.toString()
        val password = password_edittext_register.text.toString()

        Log.d("MainActivity", "Email is: $email")
        Log.d("MainActivity", "Password is: $password")

        if (email.isEmpty() || password.isEmpty())
        //return@setOnClickListener
        {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(!it.isSuccessful) return@addOnCompleteListener

                //else if successful
                Log.d("Main", "Successully created user with uid: ${it.result?.user?.uid}")
            }
            .addOnFailureListener {
                Log.d("Main", "Failed to create user: ${it.message}")

                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
