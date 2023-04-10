package io.osemwota.bankd.ui.login

import io.osemwota.bankd.redux.Action
import java.util.UUID

sealed interface LoginAction : Action {

    object LoginButtonClicked : LoginAction

    data class LoginCompleted(val id: UUID) : LoginAction

    object LoginStarted : LoginAction

    data class LoginFailed(val error: Throwable) : LoginAction

    data class UsernameChanged(val email: String) : LoginAction

    data class PasswordChanged(val password: String) : LoginAction

    data class InvalidUsername(val errorMessage: String) : LoginAction

    data class InvalidPassword(val errorMessage: String) : LoginAction

    data class ShowSnackBar(val message: String) : LoginAction

    object DismissSnackBar : LoginAction
}
