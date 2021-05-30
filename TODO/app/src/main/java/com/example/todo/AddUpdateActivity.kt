package com.example.todo

import android.annotation.SuppressLint
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
    private lateinit var taskNameTextView: TextView
    private lateinit var taskDescriptionTextView: TextView
    private var taskId: Int? = null
    private var taskName: String? = null
    private var taskDescription: String? = null

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update)

        taskNameTextView = findViewById(R.id.taskName)
        taskDescriptionTextView = findViewById(R.id.taskDescription)

        taskId = intent?.getIntExtra("taskId", 0)
        taskName = intent?.getStringExtra("taskName")
        taskDescription = intent?.getStringExtra("taskDescription")

        if (taskId != 0) {
            taskNameTextView.text = taskName
            taskDescriptionTextView.text = taskDescription
        }

        saveButton = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            taskName = taskNameTextView.text.toString()
            taskDescription = taskDescriptionTextView.text.toString()
            createOrUpdateTaskAndGoToMainActivity()
        }
    }

    private fun createOrUpdateTaskAndGoToMainActivity() {
        CoroutineScope(Dispatchers.IO).launch {
            if (taskId == 0)
                taskRepo.create(Task(Name = taskName.toString(), Description = taskDescription.toString()))
            else
                taskRepo.update(Task(Id = taskId as Int, Name = taskName.toString(), Description = taskDescription.toString()))

            withContext(Dispatchers.Main) {
                val mainActivity = Intent(this@AddUpdateActivity, MainActivity::class.java)
                startActivity(mainActivity)
            }
        }
    }
}