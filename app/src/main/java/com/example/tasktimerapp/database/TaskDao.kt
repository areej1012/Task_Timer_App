package com.example.tasktimerapp.database

import androidx.room.*


@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTask(task: Task)
    @Query("SELECT * FROM task ORDER BY id ASC")
    fun getTask(): List<Task>

    @Update
    fun updateTask(task: Task)
}