package io.osemwota.bankd.data

import io.osemwota.bankd.data.models.responses.LoginResponse

/**
 * login data source
 */
interface LoginSource {
    suspend fun login(userName: String, password: String): LoginResponse
}
