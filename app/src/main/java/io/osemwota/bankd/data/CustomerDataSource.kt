package io.osemwota.bankd.data

import io.osemwota.bankd.data.models.responses.CustomerResponse
import java.util.UUID

interface CustomerDataSource {

    suspend fun getCustomer(customerId: UUID): CustomerResponse
}