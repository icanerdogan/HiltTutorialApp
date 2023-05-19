package com.ibrahimcanerdogan.hilttutorialapp.util.mapper

interface Mapper<Entity, Response> {

    fun mapToEntity(response: Response) : Entity

    fun mapToEntityList(response: List<Response>) : List<Entity>
}