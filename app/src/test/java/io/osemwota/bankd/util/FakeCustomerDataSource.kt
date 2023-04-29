package io.osemwota.bankd.util

import io.osemwota.bankd.data.CustomerDataSource
import io.osemwota.bankd.data.models.responses.Account
import io.osemwota.bankd.data.models.responses.Customer
import io.osemwota.bankd.data.models.responses.CustomerResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.UUID

class FakeCustomerDataSource {

    companion object {
        val successSource = object : CustomerDataSource {
            override suspend fun getCustomer(customerId: UUID): Flow<Result<CustomerResponse>> {
                return flowOf(
                    Result.success(CustomerResponse(
                        id = customerId,
                        customer = Customer(
                            id = customerId,
                            firstName = "Charlie",
                            lastName = "Kabala",
                            accounts = listOf(Account("CED", 1500L,7262662, customerId)),
                        ),
                        isSuccessful = true,
                    ))
                )
            }
        }

        val failureSource = object : CustomerDataSource {
            override suspend fun getCustomer(customerId: UUID): Flow<Result<CustomerResponse>> {
                return flowOf(
                    Result.failure(Exception("Failed"))
                )
            }
        }
    }

}