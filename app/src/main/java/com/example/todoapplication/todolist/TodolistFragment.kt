package com.example.todoapplication.todolist

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapplication.R
import com.example.todoapplication.data.Task
import com.example.todoapplication.data.TaskViewModel
import com.example.todoapplication.update.UpdateTaskArgs
import kotlinx.android.synthetic.main.fragment_todolist.view.*
import kotlinx.android.synthetic.main.fragment_update_task.*
import kotlinx.android.synthetic.main.task_row.*
import kotlinx.android.synthetic.main.task_row.view.*

/**
 * Class TodolistFragment the logic for browsing the RecyclerView filled out with task rows,
 * inflating correct layout
 *
 * Class inherits from Fragment()
 */
class TodolistFragment : Fragment() {

    private lateinit var mTaskViewModel: TaskViewModel
    private val args by navArgs<TodolistFragmentArgs>()

    /**
     * Function onCreateView
     * This function is implementing the logic for browsing the RecyclerView filled out with task rows,
     * inflating correct layout.
     *
     * @param   inflater
     * @param   savedInstanceState
     * @return  view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_todolist, container, false)
        val adapter = TodolistAdapter()
        val recyclerView = view.tasksTodoRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            //findNavController().navigate(R.id.fragment)
        }

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        mTaskViewModel.getAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })


        view.addTask.setOnClickListener {
            findNavController().navigate(R.id.action_todolistFragment_to_addNewTask)
        }

        return view
    }
}

