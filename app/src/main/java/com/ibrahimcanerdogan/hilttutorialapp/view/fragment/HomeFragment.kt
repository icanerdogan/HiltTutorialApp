package com.ibrahimcanerdogan.hilttutorialapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahimcanerdogan.hilttutorialapp.R
import com.ibrahimcanerdogan.hilttutorialapp.data.locale.entities.TaskLocalEntity
import com.ibrahimcanerdogan.hilttutorialapp.databinding.FragmentHomeBinding
import com.ibrahimcanerdogan.hilttutorialapp.util.ResultState
import com.ibrahimcanerdogan.hilttutorialapp.util.event.TaskEvent
import com.ibrahimcanerdogan.hilttutorialapp.view.TaskViewModel
import com.ibrahimcanerdogan.hilttutorialapp.view.adapter.TaskAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var taskAdapter : TaskAdapter

    private val viewModel : TaskViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Adapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = taskAdapter
        }
        setObservers()
        viewModel.setTaskState(TaskEvent.GetTask)
    }

    private fun setObservers() {
        viewModel.state.observe(viewLifecycleOwner) {
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
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setProgressBar(isShown : Boolean) {
        binding.progressBar.visibility = if (isShown) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}