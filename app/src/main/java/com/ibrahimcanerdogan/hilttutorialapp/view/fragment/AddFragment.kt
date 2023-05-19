package com.ibrahimcanerdogan.hilttutorialapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.ibrahimcanerdogan.hilttutorialapp.R
import com.ibrahimcanerdogan.hilttutorialapp.data.remote.request.TaskNetworkRequest
import com.ibrahimcanerdogan.hilttutorialapp.databinding.FragmentAddBinding
import com.ibrahimcanerdogan.hilttutorialapp.util.ResultState
import com.ibrahimcanerdogan.hilttutorialapp.util.event.TaskEvent
import com.ibrahimcanerdogan.hilttutorialapp.view.TaskViewModel

class AddFragment : Fragment(R.layout.fragment_add) {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val viewModel : TaskViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()

        binding.floatingActionButtonAdd.setOnClickListener {
            addTask()
        }
    }

    private fun addTask() {
        viewModel.taskRequest.value = TaskNetworkRequest(
            userId = "1",
            title = binding.textInputEditTextTitle.text.toString(),
            body = binding.textInputEditTextBody.text.toString(),
            note = binding.textInputEditTextNote.text.toString(),
            status = "STARTED"
        )
        viewModel.setTaskState(TaskEvent.AddTask)
    }

    private fun setObserver() {
        viewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                is ResultState.Loading -> {
                    setProgressBar(true)
                }
                is ResultState.Success -> {
                    setProgressBar(false)
                    clearAllTextFields()
                }
                is ResultState.Error -> {
                    setProgressBar(false)
                    setError(it.exception.message)
                }
            }
        }
    }

    private fun setProgressBar(isShown : Boolean) {
        binding.progressBar.visibility = if (isShown) View.VISIBLE else View.GONE
    }

    private fun setError(error : String?) {
        error?.let {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearAllTextFields() {
        binding.apply {
            textInputEditTextTitle.text?.clear()
            textInputEditTextBody.text?.clear()
            textInputEditTextNote.text?.clear()
            textInputEditTextTitle.requestFocus()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}