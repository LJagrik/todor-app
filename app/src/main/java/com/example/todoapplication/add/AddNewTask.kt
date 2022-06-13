package com.example.todoapplication.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapplication.R
import com.example.todoapplication.data.Task
import com.example.todoapplication.data.TaskViewModel
import kotlinx.android.synthetic.main.fragment_add_new_task.*
import kotlinx.android.synthetic.main.fragment_add_new_task.view.*

class AddNewTask : Fragment() {

    private lateinit var mTaskViewModel: TaskViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_new_task, container, false)
        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        view.addButton.setOnClickListener {
            insertDataToDatabase()
        }


        return view
    }


    private fun insertDataToDatabase() {
        val text = newTaskText.text.toString()

        if (inputCheck(text)) {
            val task = Task(0, 0, text)
            mTaskViewModel.addTask(task)
            Toast.makeText(requireContext(), "Task added to database!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addNewTask_to_todolistFragment)
        } else {
            Toast.makeText(requireContext(), "Fill out the task text!", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(taskText: String): Boolean {
        return !(TextUtils.isEmpty(taskText))
    }



}