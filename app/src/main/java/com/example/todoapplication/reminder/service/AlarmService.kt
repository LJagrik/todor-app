package com.example.todoapplication.reminder.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.todoapplication.reminder.receiver.AlarmReceiver
import com.example.todoapplication.reminder.util.Constants
import com.example.todoapplication.reminder.util.RandomIntUtil

/**
 * Class AlarmService
 * AlarmService implemets thelogic which allows user to set exact time
 * in which the notification will trigger.
 *
 * @param context
 */
// source which helped with this code is in Word documentation
class AlarmService(private val context : Context) {
    private val alarmManager : AlarmManager? = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?

    /**
     * Function setExactAlarm
     * Allows user to set exact time
     * in which the notification will trigger.
     */
    fun setExactAlarm(timeinMillis: Long, s: String) {
        setAlarm(
            timeinMillis,
            getPendingIntent(
                getIntent().apply {
                    action = Constants.ACTION_SET_ALARM
                    putExtra(Constants.EXTRA_ALARM_TIME, timeinMillis)
                    putExtra(Constants.ALARM_TEXT, s)
                }
            )
        )
    }

    private fun setAlarm(timeinMillis: Long, pendingIntent: PendingIntent) {
        alarmManager?.let {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,timeinMillis, pendingIntent
                )
            } else {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,timeinMillis, pendingIntent
                )
            }
        }
    }

    private fun getIntent() = Intent(context, AlarmReceiver::class.java)

    private fun getPendingIntent(intent: Intent) = PendingIntent.getBroadcast(
        context, RandomIntUtil.getRandomInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
}