package com.example.todoapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Interface TaskDatabaseDao
 */
@Dao
interface TaskDatabaseDao {
    @Insert
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("DELETE FROM todo_tasks_table")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM todo_tasks_table ORDER BY taskId asc")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM todo_tasks_table WHERE taskId = :position")
    fun getTask(position: Int) : Task

}