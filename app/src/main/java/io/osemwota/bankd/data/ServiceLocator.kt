package io.osemwota.bankd.data

import io.osemwota.bankd.data.models.responses.Account
import io.osemwota.bankd.data.models.responses.Customer
import io.osemwota.bankd.data.models.responses.CustomerResponse
import io.osemwota.bankd.data.models.responses.LoginResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.util.UUID
import kotlin.random.Random

object ServiceLocator {

    private val delays: List<Long> = listOf(
        250,
        300,
        70,
        500,
        1000
    )
    private val currencies: List<String> = listOf(
        "NGN",
        "CED",
        "EUR",
        "CED"
    )

    private val firstNames: List<String> = listOf(
        "Adebola",
        "Hector",
        "Chima",
        "Adesuwa",
        "Iyore",
        "Zainab"
    )

    private val lastNames: List<String> = listOf(
        "Olukoga",
        "Petrov",
        "Agbakpan",
        "Ogunjimi",
        "Osakpolo",
        "Ochuba"
    )

    suspend fun getLoginResponse(userName: String): Flow<Result<LoginResponse>> {
        delay(delays.random())
        return when(Random.nextBoolean()) {
            true -> {
                flowOf(
                    Result.success(
                        LoginResponse(
                            isSuccessful = true,
                            errorCode = null,
                            errorMessage = null,
                            customerId = UUID.randomUUID(),
                            sessionId = UUID.randomUUID()
                        )
                    )
                )
            }
            false -> {
                flowOf(Result.failure(Exception("Login failed")))
            }
        }
    }

    suspend fun getCustomerResponse(customerId: UUID): CustomerResponse {
        delay(delays.random())
        return when(Random.nextBoolean()) {
            true -> CustomerResponse(
                id = customerId,
                customer = Customer(
                    id = customerId,
                    firstName = firstNames.random(),
                    lastName = lastNames.random(),
                    accounts = getAccounts(customerId),
                ),
                isSuccessful = true,
            )
            false -> CustomerResponse(
                id = customerId,
                customer = null,
                isSuccessful = false,
            )
        }

    }

    private fun getAccounts(customerId: UUID): List<Account> {
        return arrayListOf(
            Account(currencies.random(), 7000, 876755644545, customerId),
            Account(currencies.random(), 3500, 243698456723, customerId)
        )
    }
}