package com.example.todoapplication.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapplication.R
import com.example.todoapplication.data.Task
import com.example.todoapplication.data.TaskViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_update_task.*
import kotlinx.android.synthetic.main.fragment_update_task.view.*

class UpdateTask : Fragment() {

    private val args by navArgs<UpdateTaskArgs>()

    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //(activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update_task, container, false)

        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        view.editTaskText.setText(args.currentTask.taskText)

        view.editButton.setOnClickListener {
            updateTask()
        }

        view.buttonBackToTodolistFromEditingTask.setOnClickListener {
            findNavController().navigate(R.id.action_updateTask_to_todolistFragment)
        }

        return view
    }


    private fun updateTask() {
        val taskText = editTaskText.text.toString()

        if (inputCheck(taskText)) {
            val updatedTask = Task(args.currentTask.taskId, 0, taskText)
            mTaskViewModel.updateTask(updatedTask)
            Toast.makeText(requireContext(), "Task edited successfully!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateTask_to_todolistFragment)

        } else {
            Toast.makeText(requireContext(), "Fill out task text field!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(taskText: String): Boolean {
        return !(TextUtils.isEmpty(taskText))
    }


}