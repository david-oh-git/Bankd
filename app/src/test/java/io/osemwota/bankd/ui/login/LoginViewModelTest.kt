package io.osemwota.bankd.ui.login

import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import io.osemwota.bankd.data.repository.LoginRepository
import io.osemwota.bankd.util.FakeLoginRepository
import io.osemwota.bankd.util.MainDispatcherRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: LoginViewModel
    private lateinit var loginRepository: LoginRepository

    @Before
    fun setup() {
        loginRepository = FakeLoginRepository()
        viewModel = LoginViewModel(
            savedStateHandle = SavedStateHandle(),
            loginRepository = loginRepository,
            coroutineDispatcher = Dispatchers.Unconfined
        )
    }

    @Test
    fun stateIsNotLoadingInitially() {
        assertThat(viewModel.state.value.isLoading).isFalse()
    }

    @Test
    fun onUsernameChanged() = runTest {
        val email = "jade@gmail.com"
        viewModel.sendAction(LoginAction.UsernameChanged(email))

        val state = viewModel.state.value
        assertThat(state).isNotNull()
        assertThat(state.isLoading).isFalse()
        assertThat(state.username).isEqualTo(email)
    }

    @Test
    fun onPasswordChanged() = runTest {
        val password = "fake_password"
        viewModel.sendAction(LoginAction.PasswordChanged(password))

        val state = viewModel.state.value
        assertThat(state).isNotNull()
        assertThat(state.isLoading).isFalse()
        assertThat(state.password).isEqualTo(password)
    }

    @Test
    fun loginButtonClicked_loginActionSent() = runTest {
        val email = "jade@gmail.com"
        val password = "fake_password"
        viewModel.sendAction(LoginAction.UsernameChanged(email))
        viewModel.sendAction(LoginAction.PasswordChanged(password))


        viewModel.sendAction(LoginAction.LoginButtonClicked)

        val state = viewModel.state.value
        assertThat(state).isNotNull()
        assertThat(state.isLoading).isTrue()
        assertThat(state.username).isEqualTo(email)
        assertThat(state.password).isEqualTo(password)
    }

}