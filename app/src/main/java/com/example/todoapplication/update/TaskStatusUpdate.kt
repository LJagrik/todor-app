package com.example.todoapplication.update

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapplication.R
import com.example.todoapplication.data.Task
import com.example.todoapplication.data.TaskViewModel
import kotlinx.android.synthetic.main.fragment_update_task.*
import kotlinx.android.synthetic.main.fragment_update_task.view.*
import kotlinx.android.synthetic.main.task_row.*

/**
 * Class TaskStatusUpdate is helping with updating Task status logic
 * Class inherits from Fragment()
 */
class TaskStatusUpdate : Fragment() {

    private val args by navArgs<TaskStatusUpdateArgs>()
    private lateinit var mTaskViewModel: TaskViewModel

    /**
     * Function onCreateView
     * Inflates layout for task status update but it isn't shown for the user.
     * Implemeted logic for updating the task status - check box (checked / unchecked)
     *
     * @param   inflater
     * @param   savedInstanceState
     * @return view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        val view =  inflater.inflate(R.layout.task_status_update, container, false)

        updateTask()
        findNavController().navigate(R.id.action_taskStatusUpdate_to_todolistFragment)

        return view
    }

    private fun updateTask() {
        var taskStatus = 0
        if (args.currentTask.taskStatus == 0)
            taskStatus = 1

            val updatedTask = Task(args.currentTask.taskId, taskStatus, args.currentTask.taskText)
            mTaskViewModel.updateTask(updatedTask)
            Toast.makeText(requireContext(), "Task status edited successfully!", Toast.LENGTH_SHORT).show()
    }



}