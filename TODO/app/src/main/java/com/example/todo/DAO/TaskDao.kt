package com.example.todo.DAO

import androidx.room.*
import com.example.todo.Entities.Task
import kotlinx.serialization.Serializable

@Dao
interface TaskDao  {
    @Query("SELECT * FROM Tasks ORDER BY Id DESC")
    suspend fun getAll(): List<Task>

    @Query("SELECT * FROM Tasks WHERE Id = :id")
    suspend fun getById(id: Int): Task

    @Insert
    suspend fun create(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("DELETE FROM Tasks")
    suspend fun deleteAll()

    @Update
    suspend fun update(task: Task)
}