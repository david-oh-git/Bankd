package io.osemwota.bankd.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Acts as state container for a screen.
 * @param [initialState] The initial view state.
 * @param [reducer] Takes current state & action as input , returns resulting state.
 * @param [middlewares] List of [middlewares] for handling side effects for actions.
 */
class Store<S: State, A: Action>(
    initialState: S,
    private val reducer: Reducer<S, A>,
    private val middlewares: List<Middleware<S, A>> = emptyList(),
) {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    private val currentState: S
        get() = _state.value

    suspend fun dispatch(action: A) {
        middlewares.forEach { middleware ->
            middleware.process(action, currentState, this)
        }

        val newState = reducer.reduce(currentState, action)
        _state.value = newState
    }

}