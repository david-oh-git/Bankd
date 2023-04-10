package io.osemwota.bankd.ui.login

import io.osemwota.bankd.redux.Reducer

class LoginReducer : Reducer<LoginViewState, LoginAction> {

    override fun reduce(currentState: LoginViewState, action: LoginAction): LoginViewState {
        return when(action) {
            is LoginAction.UsernameChanged -> stateAfterEmailChanged(currentState, action)
            is LoginAction.LoginStarted -> stateAfterLoginStarted(currentState)
            is LoginAction.LoginCompleted -> stateAfterLoginComplete(currentState,action)
            is LoginAction.PasswordChanged -> stateAfterPasswordChanged(currentState,action)
            is LoginAction.InvalidPassword -> stateAfterInvalidPassword(currentState,action)
            is LoginAction.InvalidUsername -> stateAfterInvalidUsername(currentState,action)
            is LoginAction.LoginFailed -> stateAfterLoginFailed(currentState,action)
            is LoginAction.ShowSnackBar -> stateAfterShowSnackbar(currentState,action)
            is LoginAction.DismissSnackBar -> stateAfterDismissSnackBar(currentState)
            is LoginAction.LoginButtonClicked -> stateAfterLoginButtonClick(currentState)
            else -> currentState
        }
    }

    private fun stateAfterLoginButtonClick(
        currentState: LoginViewState
    ): LoginViewState {
        return currentState.copy(isLoading = true)
    }

    private fun stateAfterDismissSnackBar(
        currentState: LoginViewState
    ) = currentState.copy(snackbarMessage = null)

    private fun stateAfterLoginFailed(
        currentState: LoginViewState,
        action: LoginAction.LoginFailed
    ) = currentState.copy(
            isLoading = false, snackbarMessage = action.error.message
        )


    private fun stateAfterInvalidUsername(
        currentState: LoginViewState,
        action: LoginAction.InvalidUsername
    ) = currentState.copy(isLoading = false, snackbarMessage = action.errorMessage)

    private fun stateAfterInvalidPassword(
        currentState: LoginViewState,
        action: LoginAction.InvalidPassword
    ) = currentState.copy(isLoading = false, snackbarMessage = action.errorMessage)

    private fun stateAfterShowSnackbar(
        currentState: LoginViewState,
        action: LoginAction.ShowSnackBar
    ) = currentState.copy(isLoading = false,snackbarMessage = action.message)

    private fun stateAfterPasswordChanged(
        currentState: LoginViewState,
        action: LoginAction.PasswordChanged
    ) = currentState.copy(
        password = action.password
    )

    private fun stateAfterLoginComplete(
        currentState: LoginViewState,
        action: LoginAction.LoginCompleted
    ) = currentState.copy(isLoading = false, customerId = action.id)

    private fun stateAfterLoginStarted(
        currentState: LoginViewState
    ) = currentState.copy(isLoading = true)

    private fun stateAfterEmailChanged(
        currentState: LoginViewState,
        action: LoginAction.UsernameChanged
    ) = currentState.copy(username = action.email)
}