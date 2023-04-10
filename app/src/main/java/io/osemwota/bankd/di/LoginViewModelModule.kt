package io.osemwota.bankd.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.osemwota.bankd.data.LoginSource
import io.osemwota.bankd.data.remote.LoginSourceImpl
import io.osemwota.bankd.data.repository.LoginRepository
import io.osemwota.bankd.data.repository.LoginRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
interface LoginViewModelModule {

    @get:Binds
    val LoginSourceImpl.loginSource: LoginSource
    @get:Binds
    val LoginRepositoryImpl.loginRepository: LoginRepository
}