package io.osemwota.bankd.redux

/**
 *  Deals with the effects of actions.
 */
interface Middleware<S: State, A: Action> {

    /**
     *  Process the give [Action] and current [State] & determine if a new [Action] should
     *  be triggered.
     */
    suspend fun process(
        action: A,
        currentState: S,
        store: Store<S, A>,
    )
}