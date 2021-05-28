package com.example.todo.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val Id:Int = 0,

    val Name:String,

    val Description:String,

    val DateCreated: String = LocalDateTime.now().format(
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
)

