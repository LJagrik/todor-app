package com.example.todoapplication.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapplication.R
import com.example.todoapplication.data.TaskViewModel
import com.example.todoapplication.databinding.ActivityMainBinding
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_new_task.*
import kotlinx.android.synthetic.main.fragment_todolist.*
import kotlinx.android.synthetic.main.fragment_todolist.view.*
import kotlinx.android.synthetic.main.task_row.view.*

class TodolistFragment : Fragment() {

    private lateinit var mTaskViewModel: TaskViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_todolist, container, false)
        val adapter = TodolistAdapter()
        val recyclerView = view.tasksTodoRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        Toast.makeText(requireContext(), "Vosiel si do todolistu", Toast.LENGTH_SHORT).show()
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

