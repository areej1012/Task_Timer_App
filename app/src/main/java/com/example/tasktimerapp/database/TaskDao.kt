package com.example.tasktimerapp.database

import androidx.room.*


@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTask(task: Task)
    @Update
    suspend fun updateTask(task: Task)
}