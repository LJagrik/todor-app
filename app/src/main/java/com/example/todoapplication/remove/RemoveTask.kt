package com.example.todoapplication.remove

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapplication.R
import com.example.todoapplication.data.TaskViewModel
import com.example.todoapplication.update.UpdateTaskArgs
import kotlinx.android.synthetic.main.fragment_add_new_task.*
import kotlinx.android.synthetic.main.fragment_add_new_task.view.*
import kotlinx.android.synthetic.main.fragment_remove_task.*
import kotlinx.android.synthetic.main.fragment_remove_task.view.*
import kotlinx.android.synthetic.main.task_row.view.*

/**
 * Class RemoveTask is used for removing a new task logic
 * and also removing task from the database
 *
 * Class inherits fun OnCreateView from Fragment()
 */
class RemoveTask : Fragment() {

    private val args by navArgs<UpdateTaskArgs>()
    private lateinit var mTaskViewModel: TaskViewModel

    /**
     * Function onCreateView
     * Inflates layout for adding a new task.
     * It has ClickListener for button, so when user presses the delete button,
     * the task will be deleted from the database
     *
     * @param   inflater
     * @param   savedInstanceState
     * @return view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_remove_task, container, false)
        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        view.buttonDelete.setOnClickListener {
            deleteDataFromDatabase()
        }

        view.buttonBackToTodolistFromDeletingTask.setOnClickListener {
            findNavController().navigate(R.id.action_removeTask_to_todolistFragment)
        }

        return view
    }

    /**
     * Function deleteDataFromDatabase
     * Implemented logic for deleting taks from database
     */
    private fun deleteDataFromDatabase() {
        mTaskViewModel.deleteTask(args.currentTask)
        Toast.makeText(requireContext(), "You removed task: ${args.currentTask.taskText} ", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_removeTask_to_todolistFragment)
    }

}