package com.example.todoapplication.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Data Class Task
 * It is the model of the entity in database
 */
@Parcelize
@Entity(tableName = "todo_tasks_table")
data class Task (
    @PrimaryKey(autoGenerate = true)
    var taskId: Int = 0,

    //  @ColumnInfo(name = "task_status")
    var taskStatus : Int = 0,

    //  @ColumnInfo(name = "task_text")
    val taskText : String): Parcelable
