package com.example.todoapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapplication.login.PinChangeActivity
import com.example.todoapplication.reminder.ReminderActivity

/**
 * Class MainActivity
 * MainActivity shows the correct layouts for this activity.
 * Navigation is implemented so the user can switch between fragments of this
 * activity without problems.
 *
 * Class inherits from AppCompatActivity()
 */
class MainActivity : AppCompatActivity() {
    private lateinit var btn : ImageButton

    /**
     * Function onCreate
     * Shows the correct layout and listens if the user taps the button to switch
     * between activities.
     *
     * @param   savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.btnTodolistToReminder)
        btn.setOnClickListener {
            val b = Intent(this, ReminderActivity::class.java)
            startActivity(b)
        }
    }

    /**
     * Function onOptionsItemSelected is checking which item in the options
     * the user taps. If the user taps the item it returns true.
     *
     * @return true/false
    */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.pin_change) {
            val b = Intent(this, PinChangeActivity::class.java)
            startActivity(b)
        } else {
            return super.onOptionsItemSelected(item)
        }
        return true
    }

    /**
     * Function onCreateOptionsMenu
     * Inflating the menu.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
}