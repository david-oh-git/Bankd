package io.osemwota.bankd.ui.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.osemwota.bankd.data.repository.LoginRepository
import io.osemwota.bankd.redux.Store
import io.osemwota.bankd.redux.middleware.LoggingMiddleware
import io.osemwota.bankd.redux.middleware.LoginNetworkMiddleware
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    loginRepository: LoginRepository,
    coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val store = Store(
        initialState = LoginViewState(),
        reducer = LoginReducer(),
        middlewares = listOf(
            LoggingMiddleware(),
            LoginNetworkMiddleware(loginRepository,coroutineDispatcher)
        )
    )

    val state: StateFlow<LoginViewState> = store.state

    private val _event = MutableSharedFlow<LoginAction>()
    val event: SharedFlow<LoginAction> = _event.asSharedFlow()

    private fun onEventsChange(event: LoginAction) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    fun sendAction(action: LoginAction) {
        viewModelScope.launch {
            store.dispatch(action)
        }
    }

}