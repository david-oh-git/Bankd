package io.osemwota.bankd.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import io.osemwota.bankd.data.LoginSource
import io.osemwota.bankd.data.models.responses.LoginResponse
import javax.inject.Inject

@ViewModelScoped
class LoginRepositoryImpl @Inject constructor(
    private val loginSource: LoginSource
): LoginRepository {

    override suspend fun login(userName: String, password: String): LoginResponse {
        return loginSource.login(userName,password)
    }
}