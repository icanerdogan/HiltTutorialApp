package com.ibrahimcanerdogan.hilttutorialapp.data.remote

import com.ibrahimcanerdogan.hilttutorialapp.data.remote.response.TaskNetworkResponse
import retrofit2.http.GET

interface NetworkService {

    @GET(Endpoint.GET_ALL_TASK)
    suspend fun getAllTask() : List<TaskNetworkResponse.TaskNetworkResponseItem>
}