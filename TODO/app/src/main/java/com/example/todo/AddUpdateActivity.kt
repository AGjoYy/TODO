package com.example.todo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.Repo.TaskRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddUpdateActivity : AppCompatActivity() {

    lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update)

        saveButton = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            var mainActivity = Intent(this, MainActivity::class.java)
            startActivity(mainActivity)
        }
    }
}