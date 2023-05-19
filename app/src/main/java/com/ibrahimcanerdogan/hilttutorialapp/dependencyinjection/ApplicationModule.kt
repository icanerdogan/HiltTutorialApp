package com.ibrahimcanerdogan.hilttutorialapp.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.ibrahimcanerdogan.hilttutorialapp.data.locale.dataaccessobject.TaskDao
import com.ibrahimcanerdogan.hilttutorialapp.data.locale.database.DatabaseService
import com.ibrahimcanerdogan.hilttutorialapp.data.locale.entities.TaskEntityMapper
import com.ibrahimcanerdogan.hilttutorialapp.data.remote.NetworkBuilder
import com.ibrahimcanerdogan.hilttutorialapp.data.remote.NetworkService
import com.ibrahimcanerdogan.hilttutorialapp.repository.TaskRepositoryImpl
import com.ibrahimcanerdogan.hilttutorialapp.util.AppConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideDatabaseService(@ApplicationContext context : Context) : DatabaseService {
        return Room
            .databaseBuilder(context, DatabaseService::class.java, AppConstant.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(databaseService: DatabaseService) : TaskDao {
        return databaseService.taskDao()
    }

    @Provides
    @Singleton
    fun provideNetworkService(@ApplicationContext context: Context) : NetworkService {
        return NetworkBuilder.create(
            "https://freeapi.rdewan.dev/",
            context.cacheDir,
            (10*1024*1024).toLong()
        )
    }

    @Provides
    @Singleton
    fun provideTaskRepository(
        networkService: NetworkService,
        taskDao: TaskDao,
        taskEntityMapper: TaskEntityMapper
    ) : TaskRepositoryImpl {
        return TaskRepositoryImpl(
            networkService, taskDao, taskEntityMapper
        )
    }
}