package com.ibrahimcanerdogan.hilttutorialapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.ibrahimcanerdogan.hilttutorialapp.data.locale.entities.TaskLocalEntity
import com.ibrahimcanerdogan.hilttutorialapp.databinding.ActivityMainBinding
import com.ibrahimcanerdogan.hilttutorialapp.util.ResultState
import com.ibrahimcanerdogan.hilttutorialapp.util.event.TaskEvent
import com.ibrahimcanerdogan.hilttutorialapp.view.TaskViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel : TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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
                }

                is ResultState.Error -> {
                    setError(it.exception.message)
                }
            }
        }
    }

    private fun setTaskList(tasks : List<TaskLocalEntity>) {

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