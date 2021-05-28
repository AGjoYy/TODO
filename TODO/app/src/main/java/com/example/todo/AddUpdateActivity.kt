package com.example.todo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.Entities.Task
import com.example.todo.Repo.TaskRepo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.w3c.dom.Text
import javax.inject.Inject

@AndroidEntryPoint
class AddUpdateActivity : AppCompatActivity() {

    @Inject
    lateinit var taskRepo: TaskRepo

    private lateinit var saveButton: Button
    private lateinit var taskName: String
    private lateinit var taskDescription: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update)

        saveButton = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            taskName = findViewById<TextView>(R.id.taskName).text.toString()
            taskDescription = findViewById<TextView>(R.id.taskDescription).text.toString()
            createOrUpdateTaskAndGoToMainActivity()
        }
    }

    private fun createOrUpdateTaskAndGoToMainActivity() {
        CoroutineScope(Dispatchers.IO).launch {
            taskRepo.create(Task(Name = taskName, Description = taskDescription))
            withContext(Dispatchers.Main) {
                val mainActivity = Intent(this@AddUpdateActivity, MainActivity::class.java)
                startActivity(mainActivity)
            }
        }
    }
}