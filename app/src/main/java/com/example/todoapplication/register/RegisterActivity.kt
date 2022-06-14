package com.example.todoapplication.register

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.todoapplication.R
import com.example.todoapplication.login.LoginActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

/**
 * Class RegisterActivity
 * RegisterActivity shows layout for regiuster and checks if the PIN user types is correct.
 * The PIN must be typed 2x and must be the some both times so the user didn't have made mistake
 * in typing. SharedPreferences are used for storing the PIN.
 *
 * Class inherits from AppCompatActivity()
 */
class RegisterActivity : AppCompatActivity() {

    private lateinit var registerPin1 : EditText
    private lateinit var registerPin2 : EditText
    private lateinit var preferences : SharedPreferences

    /**
     * Function onCreate
     * Shows the correct layout and checks if the PIN is correct
     *
     * @param   savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        preferences = getSharedPreferences("PREFERENCE", 0)

        val firstTime = preferences.getString("FirstTimeInstall","")

        if (firstTime.equals("Yes")) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {


            val editor: SharedPreferences.Editor = preferences.edit()
            editor.putString("FirstTimeInstall", "Yes")
            editor.apply()


            registerPin1 = findViewById(R.id.register1)
            registerPin2 = findViewById(R.id.register2)

            registerButton.setOnClickListener {
                val pin1 = registerPin1.text.toString()
                val pin2 = registerPin2.text.toString()

                if (pin1.equals(pin2) && pin1.length >= 4) {

                    editor.putString("pin", pin1)
                    editor.apply()

                    Toast.makeText(this, "You set your PIN successfully!", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Your PIN doesnt match!", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

}