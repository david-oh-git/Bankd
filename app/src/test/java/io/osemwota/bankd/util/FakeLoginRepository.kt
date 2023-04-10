package io.osemwota.bankd.util

import io.osemwota.bankd.data.models.responses.LoginResponse
import io.osemwota.bankd.data.repository.LoginRepository

class FakeLoginRepository : LoginRepository {

    override suspend fun login(userName: String, password: String): LoginResponse {
        TODO("Not yet implemented")
    }
}