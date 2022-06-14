package com.example.todoapplication.todolist

import android.app.PendingIntent.getActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.R
import com.example.todoapplication.data.Task
import kotlinx.android.synthetic.main.task_row.view.*

/**
 * Class TodolistAdapter is used for binding and holding the tasks in RecyclerView
 *
 *
 * Class inherits from AndroidViewModel
 */
class TodolistAdapter: RecyclerView.Adapter<TodolistAdapter.MyViewHolder>() {

    private var taskList = ArrayList<Task>()
    private lateinit var holder : MyViewHolder

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_row,parent,false))
    }

    /**
     * Function onBindViewHolder
     * setting the task attributes and checking if the user tapped on any row of the RecyclerView
     *
     * @param   holder
     * @param   position
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currItem = taskList[position]
        this.holder = holder
        holder.itemView.taskTodoText.text = currItem.taskText
        holder.itemView.taskCheckBox.isChecked = toBoolean(currItem.taskStatus)


        holder.itemView.taskRowLayout.taskCheckBox.setOnClickListener {
            val action = TodolistFragmentDirections.actionTodolistFragmentToTaskStatusUpdate(currItem)
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.taskRowLayout.taskTodoText.setOnClickListener {
            val action = TodolistFragmentDirections.actionTodolistFragmentToUpdateTask(currItem)
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.image_delete.setOnClickListener {
            val action = TodolistFragmentDirections.actionTodolistFragmentToRemoveTask(currItem)
            holder.itemView.findNavController().navigate(action)
        }


    }

    private fun toBoolean(n : Int) : Boolean {
        return n!= 0
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    /**
     * Updating task list at any time
     *
     * @param task
     */
    fun setData(task: List<Task>) {
        this.taskList = task as ArrayList<Task>
        notifyDataSetChanged()
    }

    fun getItem(position: Int) : Task{
        return taskList[position]
    }

    fun deleteTask(task: List<Task>) {
        this.taskList = task as ArrayList<Task>
    }

}