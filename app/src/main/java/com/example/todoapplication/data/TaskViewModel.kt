package com.example.todoapplication.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Class TaskViewModel is used for adding a new task logic
 * and also adding task into the TaskViewModel
 *
 * Class inherits from AndroidViewModel
 * @param application
 */

class TaskViewModel(application: Application): AndroidViewModel(application) {

    val getAllData: LiveData<List<Task>>
    private val repository: TaskRepository

    /**
     * Constructor
     */
    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        getAllData = repository.getAllData
    }

    /**
     * Function addTask
     * Adding task into the repository
     *
     * @param task
     */
    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

    /**
     * Function updateTask
     * Updating task in the database
     *
     * @param task
     */
    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(task)
        }
    }

    /**
     * Function deleteTask
     * Deleting task from the database
     *
     * @param task
     */
    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(task)
        }
    }

    fun deleteAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTasks()
        }
    }

    fun getTask(position: Int): Task {
        return repository.getTask(position)
    }

}