package io.osemwota.bankd.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.osemwota.bankd.data.ServiceLocator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ActivityRetainedComponent::class)
interface DataModule {

    companion object {

        @Provides
        @ActivityRetainedScoped
        fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @Provides
        @ActivityRetainedScoped
        fun provideServiceLocator(): ServiceLocator = ServiceLocator
    }
}