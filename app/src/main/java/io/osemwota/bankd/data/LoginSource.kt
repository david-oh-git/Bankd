package io.osemwota.bankd.data

import io.osemwota.bankd.data.models.responses.LoginResponse
import kotlinx.coroutines.flow.Flow

/**
 * login data source
 */
interface LoginSource {
    suspend fun login(userName: String, password: String): Flow<Result<LoginResponse>>
}
