package com.example.todo.RecyclerViewAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.AddUpdateActivity
import com.example.todo.Entities.Task
import com.example.todo.R
import com.example.todo.Repo.TaskRepo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRecyclerViewAdapter @Inject constructor() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var taskRepo: TaskRepo? = null
    private lateinit var tasks: MutableList<Task>

    fun setTasks(tasks: List<Task>) {
        this.tasks = tasks as MutableList<Task>
    }

    fun setTaskRepository(taskRepo: TaskRepo) {
        this.taskRepo = taskRepo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rw_task_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TaskViewHolder -> {
                holder.bind(tasks[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun deleteOnSwipe(position: Int) {
        val removedTask = tasks.removeAt(position)
        notifyItemRemoved(position)

        CoroutineScope(IO).launch {
            taskRepo?.delete(removedTask)
        }
    }

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener {
                CoroutineScope(IO).launch {
                    val task = taskRepo?.getById(tasks[adapterPosition].Id)
                    withContext(Dispatchers.Main) {
                        val addActivity = Intent(view.context, AddUpdateActivity::class.java)
                        addActivity.putExtra("taskId", task?.Id)
                        addActivity.putExtra("taskName", task?.Name)
                        addActivity.putExtra("taskDescription", task?.Description)
                        view.context.startActivity(addActivity)
                    }
                }
            }
        }

        private val taskName = view.findViewById<TextView>(R.id.taskName)
        private val taskDescription = view.findViewById<TextView>(R.id.taskDescription)
        private val taskDateCreated = view.findViewById<TextView>(R.id.taskDateCreated)

        fun bind(task: Task) {
            taskName.text = task.Name
            taskDescription.text = task.Description
            taskDateCreated.text = task.DateCreated
        }
    }
}