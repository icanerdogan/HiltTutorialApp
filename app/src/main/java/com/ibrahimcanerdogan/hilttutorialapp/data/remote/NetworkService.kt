package com.ibrahimcanerdogan.hilttutorialapp.data.remote

import com.ibrahimcanerdogan.hilttutorialapp.data.remote.request.TaskNetworkRequest
import com.ibrahimcanerdogan.hilttutorialapp.data.remote.response.TaskNetworkResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkService {

    @GET(Endpoint.GET_ALL_TASK)
    suspend fun getAllTask() : List<TaskNetworkResponse.TaskNetworkResponseItem>

    @POST(Endpoint.ADD_TASK)
    suspend fun addTask(
        @Body task : TaskNetworkRequest
    ) : TaskNetworkResponse.TaskNetworkResponseItem
}