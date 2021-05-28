package com.example.todo.DAO

import androidx.room.*
import com.example.todo.Entities.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM Tasks")
    suspend fun getAll(): List<Task>

    @Query("SELECT * FROM Tasks WHERE Id = :id")
    suspend fun getById(id: Int): Task

    @Insert
    suspend fun create(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Update
    suspend fun update(task: Task)
}