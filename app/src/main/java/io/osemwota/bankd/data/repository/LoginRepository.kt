package io.osemwota.bankd.data.repository

import io.osemwota.bankd.data.models.responses.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun login(userName: String, password: String): Flow<Result<LoginResponse>>
}