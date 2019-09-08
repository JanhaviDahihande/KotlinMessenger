package com.example.kotlinmessenger

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {

            val emailLogin = email_edittext_login.text.toString()
            val passwordLogin = password_edittext_login.text.toString()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(emailLogin, passwordLogin)
                .addOnCompleteListener {
                    if(!it.isSuccessful) return@addOnCompleteListener

                    //else if successful
                    Log.d("Login", "Successully logged user with uid: ${it.result?.user?.uid}")
                }
                .addOnFailureListener {
                    Log.d("Main", "Login failed: ${it.message}")
                }
        }

        create_account_textview.setOnClickListener {
            finish()
        }
    }
}