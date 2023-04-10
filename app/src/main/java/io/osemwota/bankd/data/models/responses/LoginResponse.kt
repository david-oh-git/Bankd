package io.osemwota.bankd.data.models.responses

import androidx.annotation.Keep
import java.util.UUID

@Keep
data class LoginResponse(
    val isSuccessful: Boolean = false,
    val errorCode: Int?,
    val errorMessage: String?,
    val customerId: UUID?,
    val sessionId: UUID?
)
