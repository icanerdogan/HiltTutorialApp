package com.ibrahimcanerdogan.hilttutorialapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.ibrahimcanerdogan.hilttutorialapp.data.locale.entities.TaskLocalEntity
import com.ibrahimcanerdogan.hilttutorialapp.databinding.ItemTaskBinding

class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(data : TaskLocalEntity) {
        binding.apply {
            textViewTaskTitle.text = data.title
            textViewTaskStatus.text = data.status
            textViewTaskBody.text = data.body
        }
    }
}