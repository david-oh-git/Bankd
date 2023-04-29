package io.osemwota.bankd.data.remote

import com.google.common.truth.Truth.assertThat
import io.osemwota.bankd.data.CustomerDataSource
import io.osemwota.bankd.data.ServiceLocator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.util.UUID

class CustomerDataSourceImplTest {

    private lateinit var customerDataSource: CustomerDataSource

    @Before
    fun setUp() {
        customerDataSource = CustomerDataSourceImpl(
            ioDispatcher = Dispatchers.Unconfined,
            customerDataSource = ServiceLocator
        )
    }

    @Test
    fun customerDataResponse() = runTest {
        val id = UUID.randomUUID()

        val response = customerDataSource.getCustomer(id)

        if (response.isSuccessful) {
            assertThat(id).isEqualTo(response.id)
            assertThat(response.customer).isNotNull()
        }else {
            assertThat(response.customer).isNull()
        }
    }
}