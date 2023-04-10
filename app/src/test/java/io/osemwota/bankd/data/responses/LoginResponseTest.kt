package io.osemwota.bankd.data.responses

import com.google.common.truth.Truth.assertThat
import io.osemwota.bankd.data.models.responses.LoginResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.UUID


internal class LoginResponseTest {

    @Test
    @DisplayName("Confirm correct object attributes for failure instance of LoginResponse class")
    fun `confirm correct object attributes for failure`() {
        val isSuccessful = false
        val errorCode = 404
        val errorMessage = "Resource not found"
        val customerId = UUID.randomUUID()

        val response = LoginResponse(
            isSuccessful = isSuccessful,
            errorCode = errorCode,
            errorMessage = errorMessage,
            customerId = customerId,
            sessionId = null
        )

        assertThat(response.isSuccessful).isEqualTo(isSuccessful)
        assertThat(response.customerId).isEqualTo(customerId)
        assertThat(response.errorCode).isEqualTo(errorCode)
        assertThat(response.errorMessage).isEqualTo(errorMessage)
    }
}