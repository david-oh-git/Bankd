package io.osemwota.bankd.ui.login

import androidx.annotation.Keep

@Keep
data class LoginParams(
    val username: String,
    val password: String
)