package com.ibrahimcanerdogan.hilttutorialapp.repository

import com.ibrahimcanerdogan.hilttutorialapp.data.locale.entities.TaskLocalEntity
import com.ibrahimcanerdogan.hilttutorialapp.util.ResultState
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun getTasks() : Flow<ResultState<List<TaskLocalEntity>>>
}