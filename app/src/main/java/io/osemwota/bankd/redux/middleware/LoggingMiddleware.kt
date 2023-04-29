package io.osemwota.bankd.redux.middleware

import io.osemwota.bankd.redux.Action
import io.osemwota.bankd.redux.Middleware
import io.osemwota.bankd.redux.State
import io.osemwota.bankd.redux.Store
import timber.log.Timber

class LoggingMiddleware<S: State, A: Action> : Middleware<S, A> {

    override suspend fun process(action: A, currentState: S, store: Store<S, A>) {
        Timber.v("Action : $action , CurrentState : $currentState")
    }
}