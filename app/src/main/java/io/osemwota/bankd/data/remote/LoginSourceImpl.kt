package io.osemwota.bankd.data.remote

import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import io.osemwota.bankd.data.LoginSource
import io.osemwota.bankd.data.ServiceLocator
import io.osemwota.bankd.data.models.responses.LoginResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class LoginSourceImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val source: ServiceLocator
) : LoginSource {

    override suspend fun login(userName: String, password: String): LoginResponse = withContext(ioDispatcher){
        return@withContext source.getLoginResponse(userName)
    }
}
