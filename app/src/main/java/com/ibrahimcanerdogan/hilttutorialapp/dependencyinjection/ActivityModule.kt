package com.ibrahimcanerdogan.hilttutorialapp.dependencyinjection

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahimcanerdogan.hilttutorialapp.view.adapter.TaskAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun providesTaskAdapter() : TaskAdapter = TaskAdapter()

    @Provides
    fun provideLinearLayoutManager(@ActivityContext context : Context) = LinearLayoutManager(context)
}