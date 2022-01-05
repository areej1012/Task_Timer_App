package com.example.tasktimerapp.database

import androidx.room.*


@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPerson(person: Task)

}