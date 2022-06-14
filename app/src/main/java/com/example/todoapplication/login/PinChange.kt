package com.example.todoapplication.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.todoapplication.MainActivity
import com.example.todoapplication.R
import com.example.todoapplication.reminder.ReminderActivity
import kotlinx.android.synthetic.main.fragment_change_pin.*

class PinChange : AppCompatActivity() {

    private lateinit var preferences : SharedPreferences
    private lateinit var newPin1ET : EditText
    private lateinit var newPin2ET : EditText
    private lateinit var originalPinET : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_change_pin)

        preferences = getSharedPreferences("PREFERENCE", 0)
        val registeredPinInPref = preferences.getString("pin", "")
        val editor: SharedPreferences.Editor = preferences.edit()

        originalPinET = findViewById(R.id.originalPin)
        newPin1ET = findViewById(R.id.pinChange1)
        newPin2ET = findViewById(R.id.pinChange2)

        btnBackFromPinChange.setOnClickListener {
            val b = Intent(this, MainActivity::class.java)
            startActivity(b)
        }



        changePinButton.setOnClickListener {
            val originalPinPut = originalPin.text.toString()
            val newPin1 = newPin1ET.text.toString()
            val newPin2 = newPin2ET.text.toString()

            if (originalPinPut.equals(registeredPinInPref)) {
                if (newPin1.equals(newPin2) && newPin1.length >=4) {
                    editor.putString("pin", newPin1)
                    editor.apply()

                    Toast.makeText(this, "PINs successfully changed!", Toast.LENGTH_LONG).show()
                    val b = Intent(this, MainActivity::class.java)
                    startActivity(b)
                } else {
                    Toast.makeText(this, "New PINs doesnt match!", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "You put a wrong original PIN!", Toast.LENGTH_SHORT).show()
            }
        }

    }

}