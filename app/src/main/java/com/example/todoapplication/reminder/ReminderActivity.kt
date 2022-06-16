package com.example.todoapplication.reminder

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapplication.MainActivity
import com.example.todoapplication.R
import com.example.todoapplication.reminder.service.AlarmService
import kotlinx.android.synthetic.main.activity_reminder.*
import java.util.*

/**
 * Class ReminderActivity
 * ReminderActivity shows the correct layout for this activity.
 *
 * Class inherits from AppCompatActivity()
 */
class ReminderActivity : AppCompatActivity() {

    private lateinit var alarmService: AlarmService
    private lateinit var btn : ImageButton
    private lateinit var remindText: EditText

    /**
     * Function onCreate
     * Shows the correct layout and listens if the user taps the button to switch
     * between activities or if the user wants to add reminder.
     * If the user press the button for adding reminder the DatePickerDialog
     * and the TimePickerDialog will be shown.
     *
     * @param   savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)

        alarmService = AlarmService(this)
        btn = findViewById(R.id.btnReminderToTodolistActivity)
        remindText = findViewById(R.id.reminderText)


        btn.setOnClickListener {
            backToMainActivity()
        }

        setExact.setOnClickListener {
            val text = remindText.text.toString()
            setAlarm {timeInMillis -> alarmService.setExactAlarm(timeInMillis, text)}
        }
    }

    private fun backToMainActivity() {
        val a = Intent(this, MainActivity::class.java)
        startActivity(a)
    }

    // source which helped with this code is in Word documentation
    private fun setAlarm(callback: (Long) -> Unit) {
        Calendar.getInstance().apply {
            this.set(Calendar.SECOND,0)
            this.set(Calendar.MILLISECOND,0)
            DatePickerDialog(this@ReminderActivity,0,
                {
                                _, year, month, dayOfMonth ->
                    this.set(Calendar.YEAR,year)
                    this.set(Calendar.MONTH,month)
                    this.set(Calendar.DAY_OF_MONTH,dayOfMonth)

                    TimePickerDialog(
                        this@ReminderActivity,
                        0,
                        {
                                _, hour, min ->
                            this.set(Calendar.HOUR_OF_DAY, hour)
                            this.set(Calendar.MINUTE, min)
                            callback(this.timeInMillis)
                        },
                        this.get(Calendar.HOUR_OF_DAY),
                        this.get(Calendar.MINUTE),
                        false
                    ).show()
                },
                this.get(Calendar.YEAR),
                this.get(Calendar.MONTH),
                this.get(Calendar.DAY_OF_MONTH)).show()
        }
    }
}