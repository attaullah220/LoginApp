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

class RegisterActivity : AppCompatActivity() {

    lateinit var etName: EditText
    lateinit var etEmail: EditText
    lateinit var etConfPass: EditText
    private lateinit var etPass: EditText
    private lateinit var regPageBtn: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_register)

        etName = findViewById(R.id.editTextTextPersonName)
        etEmail = findViewById(R.id.editTextTextEmailAddress)
        etPass = findViewById(R.id.editTextTextPassword)
        etConfPass = findViewById(R.id.editTextTextPassword2)
        regPageBtn = findViewById<Button>(R.id.regPageBtn)

        auth = Firebase.auth

        regPageBtn.setOnClickListener {
            signUpUser()
        }
    }

    private fun signUpUser() {
        val name = etName.text.toString()
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        val confirmPassword = etConfPass.text.toString()

        if (name.isBlank()) {
            var etNameErr = findViewById<TextView>(R.id.tvNameErr)
            etNameErr.setText("Enter Customer Name...")
            return
        }

        else if (email.isBlank()) {
            var etMailErr = findViewById<TextView>(R.id.tvMailErr)
            etMailErr.setText("Enter Email Address...")
            return
        }

        else if (pass.isBlank()) {
            var passCodeErr = findViewById<TextView>(R.id.passCodeErr)
            passCodeErr.setText("Enter Password...")
            return
        }

        else if (confirmPassword.isBlank()) {
            var confCodeErr = findViewById<TextView>(R.id.confCodeErr)
            confCodeErr.setText("Enter Confirm Password...")
            return
        }

        var tvNameErr = findViewById<TextView>(R.id.tvNameErr)
        tvNameErr.setText("")
        var tvMailErr = findViewById<TextView>(R.id.tvMailErr)
        tvMailErr.setText("")
        var passCodeErr = findViewById<TextView>(R.id.passCodeErr)
        passCodeErr.setText("")
        var confCodeErr = findViewById<TextView>(R.id.confCodeErr)
        confCodeErr.setText("")

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                val registerIntent = Intent(this, SuccessActivity::class.java)
                startActivity(registerIntent)
            } else {
                Toast.makeText(this, "Registration Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}