package com.example.todoapplication.data

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDatabaseDao) {

    val getAllData: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun addTask(task: Task) {
        taskDao.insert(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.update(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }

    suspend fun deleteAllTasks() {
        taskDao.deleteAllTasks()
    }

    fun getTask(position: Int): Task {
        return taskDao.getTask(position)
    }
}