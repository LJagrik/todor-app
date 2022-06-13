package com.example.todoapplication.reminder.util

import java.util.concurrent.atomic.AtomicInteger

object RandomIntUtil {
    private val seed = AtomicInteger()

    public fun getRandomInt() = seed.getAndIncrement() + System.currentTimeMillis().toInt()
}