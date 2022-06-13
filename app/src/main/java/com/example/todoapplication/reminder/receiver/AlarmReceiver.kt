package com.example.todoapplication.reminder.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.todoapplication.reminder.util.Constants
import io.karn.notify.Notify

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        when(intent.action) {

            Constants.ACTION_SET_ALARM -> {
                intent.getStringExtra(Constants.ALARM_TEXT)
                    ?.let { buildNotification(context, "Reminder", it) }
            }
        }
    }

    private fun buildNotification(context: Context, title: String, message: String) {
        Notify.with(context).content {
            this.title = title
            this.text = message
        }.show()
    }

}