package com.example.todo.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.todo.Entities.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    abstract fun getAll(): List<Task>

    @Query("SELECT * FROM Task WHERE Id = :id")
    abstract fun findById(id: Int): Task

    @Insert
    abstract fun insert(task: Task)

    @Delete
    abstract fun delete(task: Task)
}