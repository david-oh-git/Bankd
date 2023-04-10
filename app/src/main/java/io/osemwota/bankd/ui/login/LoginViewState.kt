package io.osemwota.bankd.ui.login

import androidx.annotation.Keep
import io.osemwota.bankd.redux.State
import java.util.UUID

@Keep
data class LoginViewState(
    val isLoading: Boolean = false,
    val customerId: UUID? = null,
    val snackbarMessage: String? = null,
    val username: String = "",
    val password: String = "",
) : State