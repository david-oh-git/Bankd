package io.osemwota.bankd.data

import io.osemwota.bankd.data.models.responses.Account
import io.osemwota.bankd.data.models.responses.Customer
import io.osemwota.bankd.data.models.responses.CustomerResponse
import io.osemwota.bankd.data.models.responses.LoginResponse
import kotlinx.coroutines.delay
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
        "EUR"
    )

    suspend fun getLoginResponse(userName: String): LoginResponse {

        return when(Random.nextBoolean()) {
            true -> {
                delay(delays.random())
                LoginResponse(
                    isSuccessful = true,
                    errorCode = null,
                    errorMessage = null,
                    customerId = UUID.randomUUID(),
                    sessionId = UUID.randomUUID()
                )
            }
            false -> {
                delay(delays.random())
                LoginResponse(
                    isSuccessful = false,
                    errorCode = 408,
                    errorMessage = "Request timed out",
                    customerId = null,
                    sessionId = null
                )
            }
        }
    }

    private fun getCustomerResponse(customerId: UUID): CustomerResponse {
        return CustomerResponse(
            id = customerId,
            customer = Customer(
                id = customerId,
                firstName = "",
                lastName = "",
                accounts = getAccounts(customerId)
            )
        )
    }

    private fun getAccounts(customerId: UUID): List<Account> {
        return arrayListOf(
            Account(currencies.random(), 7000, 876755644545, customerId),
            Account(currencies.random(), 3500, 243698456723, customerId)
        )
    }
}