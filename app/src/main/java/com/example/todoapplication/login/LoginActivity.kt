package com.example.todoapplication.login

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.navigation.ui.AppBarConfiguration
import com.example.todoapplication.MainActivity
import com.example.todoapplication.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginPin : EditText
    private lateinit var preferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginPin = findViewById(R.id.pinLogin)
        preferences = getSharedPreferences("PREFERENCE",0)
        val registeredPin = preferences.getString("pin","")


        loginButton.setOnClickListener {
            val pin = loginPin.text.toString()
            if (pin.equals(registeredPin)) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Wrong PIN!", Toast.LENGTH_SHORT).show()

            }
        }

    }
}