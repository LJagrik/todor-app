package com.example.todoapplication.remove

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

class RemoveTask : Fragment() {

    private val args by navArgs<UpdateTaskArgs>()
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_remove_task, container, false)
        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        view.buttonDelete.setOnClickListener {
            deleteDataFromDatabase()
        }



        return view
    }


    private fun deleteDataFromDatabase() {
        mTaskViewModel.deleteTask(args.currentTask)
        Toast.makeText(requireContext(), "You removed task: ${args.currentTask.taskText} ", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_removeTask_to_todolistFragment)
    }

    private fun inputCheck(taskText: String): Boolean {
        return !(TextUtils.isEmpty(taskText))
    }
}