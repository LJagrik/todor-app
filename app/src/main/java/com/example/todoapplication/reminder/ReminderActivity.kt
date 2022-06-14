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

class ReminderActivity : AppCompatActivity() {

    private lateinit var alarmService: AlarmService
    private lateinit var btn : ImageButton
    private lateinit var remindText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)

        alarmService = AlarmService(this)
        btn = findViewById(R.id.btnReminderToTodolistActivity)
        remindText = findViewById(R.id.reminderText)


        btn.setOnClickListener {
            val a = Intent(this, MainActivity::class.java)
            startActivity(a)
        }

        setExact.setOnClickListener {
            val text = remindText.text.toString()
            setAlarm {timeInMillis -> alarmService.setExactAlarm(timeInMillis, text)}
        }

    }

    private fun setAlarm(callback: (Long) -> Unit) {
        Calendar.getInstance().apply {
            this.set(Calendar.SECOND,0)
            this.set(Calendar.MILLISECOND,0)
            DatePickerDialog(this@ReminderActivity,0,
                DatePickerDialog.OnDateSetListener {
                                _, year, month, dayOfMonth ->
                    this.set(Calendar.YEAR,year)
                    this.set(Calendar.MONTH,month)
                    this.set(Calendar.DAY_OF_MONTH,dayOfMonth)

                    TimePickerDialog(
                        this@ReminderActivity,
                        0,
                        TimePickerDialog.OnTimeSetListener {
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