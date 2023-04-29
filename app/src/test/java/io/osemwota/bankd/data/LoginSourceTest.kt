package io.osemwota.bankd.data

import com.google.common.truth.Truth.assertThat
import io.osemwota.bankd.data.remote.LoginSourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
class LoginSourceTest {

    private lateinit var loginSource: LoginSource

    @Before
    fun setup() {
        loginSource = LoginSourceImpl(
            ioDispatcher = Dispatchers.Unconfined,
            source = ServiceLocator
        )
    }

    @Test
    fun loginResponse() = runTest {
        val result = loginSource.login("username","password").first()
        if (result.isSuccess) {
            val response = result.getOrNull()
            assertThat(response).isNotNull()
            assertThat(response?.errorMessage).isNull()
            assertThat(response?.errorCode).isNull()
            assertThat(response?.customerId).isNotNull()
            assertThat(response?.sessionId).isNotNull()
        }else{
            assertThat(result.isFailure).isTrue()
        }
    }
}