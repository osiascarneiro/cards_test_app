package cards.features.accountdetails.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import cards.core.model.RequestState
import cards.features.accountdetails.mock.AccountMocks
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
        val returns = AccountMocks.createSuccessResponse()
        coEvery { service.getAccountDetail(accountId) } returns returns
        //When
        val data = runBlocking { sut.getAccountDetail(accountId) }
        //Then
        assert(data is RequestState.Success)
        assert((data as RequestState.Success).result.balance.label == "asd")
        assert(data.result.balance.value == "123,00")
        assert(data.result.transactions.isEmpty())
    }

    @Test
    fun `Should return failure`() {
        //Given
        val accountId = "1432"
        val returns = AccountMocks.createFailureResponse()
        coEvery { service.getAccountDetail(accountId) } returns returns
        //When
        val data = runBlocking { sut.getAccountDetail(accountId) }
        //Then
        assert(data is RequestState.Failure)
        assert((data as RequestState.Failure).error.message == "Error in getting account")
    }



}