package io.osemwota.bankd.data.repository

import io.osemwota.bankd.data.models.responses.LoginResponse

interface LoginRepository {

    suspend fun login(userName: String, password: String): LoginResponse
}