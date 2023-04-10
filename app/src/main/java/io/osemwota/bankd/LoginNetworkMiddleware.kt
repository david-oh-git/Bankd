package io.osemwota.bankd

import io.osemwota.bankd.data.repository.LoginRepository
import io.osemwota.bankd.redux.Middleware
import io.osemwota.bankd.redux.Store
import io.osemwota.bankd.ui.login.LoginAction
import io.osemwota.bankd.ui.login.LoginViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LoginNetworkMiddleware(
    private val loginRepository: LoginRepository,
    private val coroutineDispatcher: CoroutineDispatcher
)  : Middleware<LoginViewState, LoginAction> {

    override suspend fun process(
        action: LoginAction,
        currentState: LoginViewState,
        store: Store<LoginViewState, LoginAction>
    ) {
        when(action) {
            is LoginAction.LoginButtonClicked -> {
                if (currentState.username.isNotBlank() && currentState.password.isNotBlank())
                    login(currentState, store)
            }

            else -> {}
        }
    }

    private suspend fun login(
        currentState: LoginViewState,
        store: Store<LoginViewState, LoginAction>
    ) = withContext(coroutineDispatcher) {
        val response = loginRepository.login(
            currentState.username,
            currentState.password
        )
        if (response.isSuccessful && response.customerId != null) {
            store.dispatch(LoginAction.LoginCompleted(response.customerId))
        }else {
            store.dispatch(
                LoginAction.LoginFailed(Error(response.errorMessage))
            )
        }
    }
}