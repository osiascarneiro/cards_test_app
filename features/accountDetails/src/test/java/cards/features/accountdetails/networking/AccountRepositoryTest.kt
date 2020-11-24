package cards.features.accountdetails.networking

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import cards.core.model.ApiResult
import cards.features.accountdetails.model.AccountDetail
import cards.features.accountdetails.model.Balance
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class AccountRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var sut: AccountRepository
    private val service = mockk<AccountService>()

    @Before
    fun setup() {
        sut = AccountRepository(service)
    }

    @Test
    fun `Should return success`() {
        //Given
        val accountId = "1432"
        val account = AccountDetail(Balance("", ""), emptyList())
        val returns = Response.success(account)
        coEvery { service.getAccountDetail(accountId) } returns returns
        //When
        val data = runBlocking { sut.getAccountDetail(accountId) }
        //Then
        assert(data is ApiResult.Success)
        assert((data as ApiResult.Success).result === account)
    }

    @Test
    fun `Should return failure`() {
        //Given
        val accountId = "1432"
        val returns = Response.error<AccountDetail>(404, ResponseBody.create(MediaType.get("application/json"), "Error in getting account"))
        coEvery { service.getAccountDetail(accountId) } returns returns
        //When
        val data = runBlocking { sut.getAccountDetail(accountId) }
        //Then
        assert(data is ApiResult.Failure)
        assert((data as ApiResult.Failure).error.message == "Error in getting account")
    }



}