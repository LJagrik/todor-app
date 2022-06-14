package com.example.todoapplication.data

import androidx.lifecycle.LiveData

/**
 * Class TaskRepository is used for communicationg with TaskDatabaseDao (adding, updating deleting tasks
 * into / from database)
 *
 * @param   taskDao
 */
class TaskRepository(private val taskDao: TaskDatabaseDao) {

    val getAllData: LiveData<List<Task>> = taskDao.getAllTasks()

    /**
     * Function addTask
     *
     * @param   task
     */
    suspend fun addTask(task: Task) {
        taskDao.insert(task)
    }

    /**
     * Function updateTask
     *
     * @param   task
     */
    suspend fun updateTask(task: Task) {
        taskDao.update(task)
    }

    /**
     * Function delete
     *
     * @param   task
     */
    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }

    suspend fun deleteAllTasks() {
        taskDao.deleteAllTasks()
    }

    /**
     * Function getTask
     * returns position of the task in the database
     *
     * @param   position
     * @return  task
     */
    fun getTask(position: Int): Task {
        return taskDao.getTask(position)
    }
}