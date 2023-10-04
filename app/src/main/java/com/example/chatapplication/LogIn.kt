package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {

    private lateinit var  editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var buttonLogIn: Button
    private lateinit var buttonSignUp: Button

    private lateinit var mAuthentication: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        supportActionBar?.hide()

        mAuthentication = FirebaseAuth.getInstance()

        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        buttonLogIn = findViewById(R.id.buttonLogIn)
        buttonSignUp = findViewById(R.id.buttonSignUp)

        buttonSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        buttonLogIn.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()

            logIn(email, password)
        }

    }
    private fun logIn(email: String, password: String){
        // logic for login users
        mAuthentication.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // code for log in user
                    val intent = Intent(this@LogIn, MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(this@LogIn, "User does not Exit", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
