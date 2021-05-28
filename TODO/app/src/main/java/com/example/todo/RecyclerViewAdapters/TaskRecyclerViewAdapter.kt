package com.example.todo.RecyclerViewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.Entities.Task
import com.example.todo.R
import javax.inject.Inject


class TaskRecyclerViewAdapter @Inject constructor(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var tasks : List<Task>

    fun setTasks(tasks : List<Task>){
        this.tasks = tasks
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rw_task_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is TaskViewHolder -> {
                holder.bind(tasks[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    class TaskViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        private val taskName = view.findViewById<TextView>(R.id.taskName)
        private val taskDescription = view.findViewById<TextView>(R.id.taskDescription)
        private val taskDateCreated = view.findViewById<TextView>(R.id.taskDateCreated)

        fun bind(task : Task){
            taskName.text = task.Name
            taskDescription.text = task.Description
            taskDateCreated.text = task.DateCreated
        }

    }
}