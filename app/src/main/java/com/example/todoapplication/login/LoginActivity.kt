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

/**
 * Class LoginActivity
 * LoginActivity shows layout for login and checks if the PIN user types is correct.
 * If the PIN is correct the user will be brought to the MainActivity, if the PIN is
 * incorrect the user will be notified. SharedPreferences are used for checking the PIN.
 *
 * Class inherits from AppCompatActivity()
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var loginPin : EditText
    private lateinit var preferences : SharedPreferences

    /**
     * Function onCreate
     * Shows the correct layout and checks if the PIN is correct
     *
     * @param   savedInstanceState
     */
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