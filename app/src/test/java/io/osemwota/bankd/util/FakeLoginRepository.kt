package io.osemwota.bankd.util

import io.osemwota.bankd.data.models.responses.LoginResponse
import io.osemwota.bankd.data.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class FakeLoginRepository : LoginRepository {

    override suspend fun login(userName: String, password: String): Flow<Result<LoginResponse>> {
        TODO("Not yet implemented")
    }
}