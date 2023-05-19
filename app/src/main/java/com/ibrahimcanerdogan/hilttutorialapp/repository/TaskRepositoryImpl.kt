package com.ibrahimcanerdogan.hilttutorialapp.repository

import com.ibrahimcanerdogan.hilttutorialapp.data.locale.dataaccessobject.TaskDao
import com.ibrahimcanerdogan.hilttutorialapp.data.locale.entities.TaskEntityMapper
import com.ibrahimcanerdogan.hilttutorialapp.data.locale.entities.TaskLocalEntity
import com.ibrahimcanerdogan.hilttutorialapp.data.remote.NetworkService
import com.ibrahimcanerdogan.hilttutorialapp.data.remote.request.TaskNetworkRequest
import com.ibrahimcanerdogan.hilttutorialapp.util.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class TaskRepositoryImpl(
    private val networkService: NetworkService,
    private val taskDao: TaskDao,
    private val taskEntityMapper: TaskEntityMapper
) : TaskRepository {

    override suspend fun getTasks(): Flow<ResultState<List<TaskLocalEntity>>> = flow {
        emit(ResultState.Loading)
        try {
            val response = networkService.getAllTask()
            val taskList = taskEntityMapper.mapToEntityList(response)
            taskDao.insertMany(taskList)

            val task = taskDao.getTasks()
            emit(ResultState.Success(task))
        } catch (e : Exception) {
            emit(ResultState.Error(e))
        }
    }

    override suspend fun addTask(task: TaskNetworkRequest): Flow<ResultState<List<TaskLocalEntity>>> = flow {
        emit(ResultState.Loading)
        try {
            val response = networkService.addTask(task)
            val taskResponse = taskEntityMapper.mapToEntity(response)
            taskDao.insert(taskResponse)

            emit(ResultState.Success(taskDao.getTasks()))
        } catch (e : Exception) {
            emit(ResultState.Error(e))
        }
    }
}