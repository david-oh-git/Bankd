package io.osemwota.bankd.data.models.responses

import androidx.annotation.Keep
import java.util.UUID

@Keep
data class CustomerResponse(
    val id: UUID,
    val customer: Customer?,
    val isSuccessful: Boolean = false,
)


@Keep
data class Customer(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val accounts: List<Account>
)

/**
 * @param currency Currency code
 * @param balance Account balance available
 * @param number Account number
 * @param customerId Unique id that identifies the customer.
 */
@Keep
data class Account(
    val currency: String,
    val balance: Long,
    val number: Long,
    val customerId: UUID
)