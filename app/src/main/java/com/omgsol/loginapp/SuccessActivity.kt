package com.omgsol.loginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_successfull)

        //lateinit var registerActivity: RegisterActivity

        var txtViewName = findViewById<TextView>(R.id.txtViewName)
        txtViewName.setText("We welcome to our Login Application.")

        val regSuccessBtn = findViewById<Button>(R.id.backBtn)
        regSuccessBtn.setOnClickListener {
            val newLoginIntent = Intent(this, MainActivity::class.java)
            startActivity(newLoginIntent)
        }
    }
}