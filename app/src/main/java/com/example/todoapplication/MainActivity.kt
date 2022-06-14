package com.example.todoapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.todoapplication.login.PinChange
import com.example.todoapplication.reminder.ReminderActivity


class MainActivity : AppCompatActivity() {

    private lateinit var btn : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        val appBarConfiguration = AppBarConfiguration
//            .Builder(R.id.todolistFragment)
//            .build()


        setContentView(R.layout.activity_main)
        //setupActionBarWithNavController(findNavController(R.id.fragment), appBarConfiguration)


        btn = findViewById(R.id.btnTodolistToReminder)
        btn.setOnClickListener {
            val b = Intent(this, ReminderActivity::class.java)
            startActivity(b)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.pin_change) {
            val b = Intent(this, PinChange::class.java)
            startActivity(b)
        } else {
            return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.fragment)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }


}