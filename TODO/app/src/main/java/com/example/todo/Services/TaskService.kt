//package com.example.todo.Services
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.todo.Entities.Task
//import com.example.todo.Repo.TaskRepo
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.Dispatchers.IO
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import javax.inject.Inject
//
//class TaskService @Inject constructor(private val taskRepo: TaskRepo) : ViewModel() {
//
////    fun getAll(): List<Task> {
////        lateinit var tasks: List<Task>
////        CoroutineScope(IO).launch {
////            tasks = taskRepo.getAll()
////            println(tasks)
////        }
////        return tasks
////    }
//
//    suspend fun getAll(): List<Task> {
//      return taskRepo.getAll()
//    }
//
//    fun getById(id: Int): Task {
//        lateinit var task: Task
//        CoroutineScope(IO).launch {
//            task = taskRepo.getById(id)
//        }
//        return task
//    }
//
//    fun delete(task: Task) {
//        viewModelScope.launch {
//            taskRepo.delete(task)
//        }
//    }
//
//    fun create(task: Task) {
//        CoroutineScope(IO).launch {
//            taskRepo.create(task)
//        }
//    }
//
//    fun update(task: Task) {
//        viewModelScope.launch {
//            taskRepo.update(task)
//        }
//    }
//}