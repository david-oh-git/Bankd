package io.osemwota.bankd.data.remote

import io.osemwota.bankd.data.CustomerDataSource
import io.osemwota.bankd.data.ServiceLocator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class CustomerDataSourceImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val customerDataSource: ServiceLocator
) : CustomerDataSource {

    /**
     * Mock retrieving customer data from z cloud . :( :(
     */
    override suspend fun getCustomer(customerId: UUID) = withContext(ioDispatcher) {
        return@withContext customerDataSource.getCustomerResponse(customerId)
    }
}