package io.osemwota.bankd.data

import com.google.common.truth.Truth.assertThat
import io.osemwota.bankd.data.remote.LoginSourceImpl
import kotlinx.coroutines.Dispatchers
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
        val response = loginSource.login("username","password")
        if (response.isSuccessful) {
            assertThat(response.errorMessage).isNull()
            assertThat(response.errorCode).isNull()
            assertThat(response.customerId).isNotNull()
        }else{
            assertThat(response.customerId).isNull()
            assertThat(response.errorMessage).isNotNull()
            assertThat(response.errorCode).isNotNull()
        }
    }
}