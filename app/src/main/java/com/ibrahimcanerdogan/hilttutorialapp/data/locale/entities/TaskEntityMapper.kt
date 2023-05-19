package com.ibrahimcanerdogan.hilttutorialapp.data.locale.entities

import com.ibrahimcanerdogan.hilttutorialapp.data.remote.response.TaskNetworkResponse
import com.ibrahimcanerdogan.hilttutorialapp.util.mapper.Mapper

class TaskEntityMapper : Mapper<TaskLocalEntity, TaskNetworkResponse.TaskNetworkResponseItem> {

    override fun mapToEntity(response: TaskNetworkResponse.TaskNetworkResponseItem): TaskLocalEntity {
        return TaskLocalEntity(
            id = response.id.toInt(),
            userId = response.userId.toInt(),
            title =  response.title,
            body = response.body,
            status = response.status,
            note = response.note,
            createdAt = response.createdAt,
            updatedAt = response.updatedAt
        )
    }

    override fun mapToEntityList(response: List<TaskNetworkResponse.TaskNetworkResponseItem>): List<TaskLocalEntity> {
        return response.map {
            mapToEntity(it)
        }
    }

}