package com.ibrahimcanerdogan.hilttutorialapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ibrahimcanerdogan.hilttutorialapp.data.locale.entities.TaskLocalEntity
import com.ibrahimcanerdogan.hilttutorialapp.databinding.ItemTaskBinding

class TaskAdapter : RecyclerView.Adapter<TaskViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<TaskLocalEntity>() {
        override fun areItemsTheSame(oldItem: TaskLocalEntity, newItem: TaskLocalEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TaskLocalEntity,
            newItem: TaskLocalEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun setData(newList : List<TaskLocalEntity>) {
        asyncListDiffer.submitList(newList)
    }

    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size

    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        asyncListDiffer.currentList[position]?.let {
            holder.bindData(it)
        }
    }
}