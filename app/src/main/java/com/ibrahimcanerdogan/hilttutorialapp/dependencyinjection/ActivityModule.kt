package com.ibrahimcanerdogan.hilttutorialapp.dependencyinjection

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {
}