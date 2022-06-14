package com.example.todoapplication.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapplication.R
import com.example.todoapplication.data.Task
import com.example.todoapplication.data.TaskViewModel
import kotlinx.android.synthetic.main.fragment_add_new_task.*
import kotlinx.android.synthetic.main.fragment_add_new_task.view.*

/**
 * Class AddNewTask is used for adding a new task logic
 * and also adding task into the TaskViewModel
 *
 * Class inherits fun OnCreateView from Fragment()
 */
class AddNewTask : Fragment() {

    private lateinit var mTaskViewModel: TaskViewModel

    /**
     * Function onCreateView
     * Inflates layout for adding a new task.
     * It has ClickListener for button, so when user presses the add button,
     * the task will be inserted into the database
     *
     * @param   inflater
     * @param   savedInstanceState
     * @return view
     */
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

    /**
     * Function insertDataToDatabase()
     * adding Task attributes into the database
     */
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

    /**
     * Function inputCheck
     * Checks if the string isn't empty
     *
     * @param   taskText
     * @return  true/false
     */
    private fun inputCheck(taskText: String): Boolean {
        return !(TextUtils.isEmpty(taskText))
    }
}