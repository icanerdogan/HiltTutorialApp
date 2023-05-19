package com.ibrahimcanerdogan.hilttutorialapp.data.locale.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ibrahimcanerdogan.hilttutorialapp.data.locale.dataaccessobject.TaskDao
import com.ibrahimcanerdogan.hilttutorialapp.data.locale.entities.TaskLocalEntity

@Database(entities = [TaskLocalEntity::class], version = 2, exportSchema = false)
abstract class DatabaseService : RoomDatabase() {

    abstract fun taskDao() : TaskDao

}