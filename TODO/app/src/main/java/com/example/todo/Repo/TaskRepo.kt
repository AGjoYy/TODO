package com.example.todo.Repo

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.DAO.TaskDao
import com.example.todo.DB.AppDatabase
import com.example.todo.Entities.Task
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlinx.serialization.Serializable


class TaskRepo @Inject constructor(@ApplicationContext context: Context) {

    private var taskDao: TaskDao

    init {
        val database: AppDatabase = AppDatabase.getInstance(context)
        taskDao = database.taskDao()
    }

    suspend fun getAll(): List<Task> {
        return taskDao.getAll()
    }

    suspend fun getById(id: Int): Task {
        return taskDao.getById(id)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }

    suspend fun deleteAll() {
        taskDao.deleteAll()
    }

    suspend fun create(task: Task) {
        taskDao.create(task)
    }


    suspend fun update(task: Task) {
        taskDao.update(task)
    }
}