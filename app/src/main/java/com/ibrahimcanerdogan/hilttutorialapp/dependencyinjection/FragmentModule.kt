package com.ibrahimcanerdogan.hilttutorialapp.dependencyinjection

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object FragmentModule {
}