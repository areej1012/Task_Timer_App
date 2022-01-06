package com.example.tasktimerapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var title: String,
    var description: String,
    var total_Time: Long
) : Serializable
