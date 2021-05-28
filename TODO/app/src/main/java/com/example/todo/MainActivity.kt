package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.OnClickAction
import android.widget.Button
import com.example.todo.Repo.TaskRepo
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var taskRepo: TaskRepo

    lateinit var addButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton = findViewById(R.id.addButton)
//    //    addButton.setOnClickListener {
//            val addActivity = Intent(this, AddUpdateActivity::class.java)
//            startActivity(addActivity)
//        }
    }

//    CoroutineScope(IO).launch
//    {
//        var test = taskRepo.getAll();
//        print(test)
//    }
}
