package com.example.todo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.RecyclerViewAdapters.TaskRecyclerViewAdapter
import com.example.todo.Repo.TaskRepo
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var taskRepo: TaskRepo

    @Inject
    lateinit var taskRecyclerViewAdapter: TaskRecyclerViewAdapter

    lateinit var addButton: FloatingActionButton
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            val addActivity = Intent(this, AddUpdateActivity::class.java)
            startActivity(addActivity)
        }

        recyclerView = findViewById(R.id.taskItem)
        recyclerViewInit();
    }

    private fun recyclerViewInit(){
        CoroutineScope(IO).launch {
            //taskRepo.deleteAll()
            val tasks = taskRepo.getAll()
            withContext(Dispatchers.Main) {
                taskRecyclerViewAdapter.setTasks(tasks)
                recyclerViewApply()
            }
        }
    }
    private fun recyclerViewApply() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskRecyclerViewAdapter
        }
    }
}
