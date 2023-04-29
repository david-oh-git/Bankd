package io.osemwota.bankd.data.remote

import com.google.common.truth.Truth.assertThat
import io.osemwota.bankd.data.CustomerDataSource
import io.osemwota.bankd.data.ServiceLocator
import io.osemwota.bankd.util.FakeCustomerDataSourceFailure
import io.osemwota.bankd.util.FakeCustomerDataSourceSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.util.UUID

class CustomerDataSourceTest {

    private lateinit var customerDataSource: CustomerDataSource


    @Test
    fun customerDataResponse_Success() = runTest {
        customerDataSource = FakeCustomerDataSourceSuccess()
        val id = UUID.randomUUID()

        val result = customerDataSource.getCustomer(id).first()

        assertThat(result.isSuccess).isTrue()
        val response = result.getOrNull()
        assertThat(response).isNotNull()
        assertThat(id).isEqualTo(response?.id)
        assertThat(response?.customer).isNotNull()

    }

    @Test
    fun customerDataResponse_Failure() = runTest {
        customerDataSource = FakeCustomerDataSourceFailure()
        val id = UUID.randomUUID()

        val result = customerDataSource.getCustomer(id).first()
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isNotNull()
    }


}