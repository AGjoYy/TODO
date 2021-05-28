package com.example.todo.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todo.DAO.TaskDao
import com.example.todo.Entities.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            if (DbInstance.instance == null) {
                DbInstance.instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java, "TODO"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return DbInstance.instance!!
        }
    }
}

object DbInstance {
    var instance : AppDatabase? = null
}