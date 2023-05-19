package com.ibrahimcanerdogan.hilttutorialapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahimcanerdogan.hilttutorialapp.data.locale.entities.TaskLocalEntity
import com.ibrahimcanerdogan.hilttutorialapp.databinding.ActivityMainBinding
import com.ibrahimcanerdogan.hilttutorialapp.util.ResultState
import com.ibrahimcanerdogan.hilttutorialapp.util.event.TaskEvent
import com.ibrahimcanerdogan.hilttutorialapp.view.adapter.TaskAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var taskAdapter : TaskAdapter
    @Inject
    lateinit var linearLayoutManager : LinearLayoutManager

    private lateinit var binding: ActivityMainBinding
    private val viewModel : TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Adapter
        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = taskAdapter
        }
        setObservers()
        viewModel.setTaskState(TaskEvent.GetTask)
    }

    private fun setObservers() {
        viewModel.state.observe(this) {
            when (it) {
                is ResultState.Loading -> {
                    setProgressBar(true)
                }

                is ResultState.Success -> {
                    setTaskList(it.data)
                    setProgressBar(false)
                }

                is ResultState.Error -> {
                    setError(it.exception.message)
                    setProgressBar(false)
                }
            }
        }
    }

    private fun setTaskList(tasks : List<TaskLocalEntity>) {
        taskAdapter.setData(tasks)
    }

    private fun setError(error : String?) {
        error?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setProgressBar(isShown : Boolean) {
        binding.progressBar.visibility = if (isShown) View.VISIBLE else View.GONE
    }
}