package com.ibrahimcanerdogan.hilttutorialapp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimcanerdogan.hilttutorialapp.data.locale.entities.TaskLocalEntity
import com.ibrahimcanerdogan.hilttutorialapp.repository.TaskRepositoryImpl
import com.ibrahimcanerdogan.hilttutorialapp.util.ResultState
import com.ibrahimcanerdogan.hilttutorialapp.util.event.TaskEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskRepositoryImpl: TaskRepositoryImpl
) : ViewModel() {

    private val _state: MutableLiveData<ResultState<List<TaskLocalEntity>>> = MutableLiveData()
    val state : LiveData<ResultState<List<TaskLocalEntity>>>
        get() = _state

    fun setTaskState(event: TaskEvent) {
        viewModelScope.launch {
            when(event) {
                is TaskEvent.GetTask -> {
                    taskRepositoryImpl.getTasks()
                        .onEach {
                            _state.value = it
                        }
                        .launchIn(viewModelScope)
                }
                is TaskEvent.AddTask -> {

                }
                is TaskEvent.DeleteTask -> {

                }
                is TaskEvent.None -> {

                }
                else -> {}
            }
        }
    }
}