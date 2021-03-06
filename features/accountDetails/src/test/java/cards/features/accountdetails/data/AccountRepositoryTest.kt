package cards.features.accountdetails.data

import cards.core.model.RequestState
import cards.core.test.base.BaseTest
import cards.features.accountdetails.mock.AccountMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AccountRepositoryTest: BaseTest() {

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