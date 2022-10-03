package com.omgsol.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var etEmail: EditText
    lateinit var etPass: EditText
    lateinit var btnLogin: Button
    lateinit var btnRegister: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEmail = findViewById(R.id.emailText)
        etPass = findViewById(R.id.passcoodeText)
        btnLogin = findViewById(R.id.loginBtn)
        btnRegister = findViewById(R.id.registerBtn)

        //auth = Firebase.auth
        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            login()
        }

        btnRegister.setOnClickListener {
            val register = Intent(this, RegisterActivity::class.java)
            startActivity(register)
        }
    }

    // [START on_start_check_user]
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }

    fun login() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()

        if (email.isBlank()) {
            var etEmailErr = findViewById<TextView>(R.id.etEmailErr)
            etEmailErr.setText("Enter Email Address...")
            return
        }

        else if (pass.isBlank()) {
            var etPasscodeErr = findViewById<TextView>(R.id.etPasscodeErr)
            etPasscodeErr.setText("Enter Password...")
            return
        }
            var etEmailErr = findViewById<TextView>(R.id.etEmailErr)
            etEmailErr.setText("")
            var etPasscodeErr = findViewById<TextView>(R.id.etPasscodeErr)
            etPasscodeErr.setText("")

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                val loginIntent = Intent(this, LoginSuccess::class.java)
                startActivity(loginIntent)
            } else
                Toast.makeText(this, "User not found or Check your internet connection...!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun reload() {

    }

}