package cards.features.accountdetails.viewmodel

import cards.core.model.RequestState
import cards.core.test.base.BaseTest
import cards.core.test.rule.MainCoroutineRule
import cards.core.test.util.getOrAwaitValue
import cards.features.accountdetails.data.AccountRepositoryInterface
import cards.features.accountdetails.mock.AccountMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AccountViewModelTest: BaseTest() {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var sut: AccountViewModel
    private val repository = mockk<AccountRepositoryInterface>()

    @Before
    fun setup() {
        sut = AccountViewModel(repository)
    }

    @Test
    fun `Should return success`() {
        //Given
        val accountId = "1432"
        val returns = AccountMocks.createSuccessRequestState()
        coEvery { repository.getAccountDetail(accountId) } returns returns
        //When
        sut.accountId = accountId
        //Then
        val data = sut.accountLiveData.getOrAwaitValue()
        assert(data is RequestState.Success)
        assert((data as RequestState.Success).result.balance.label == "asd")
        assert(data.result.balance.value == "123,00")
        assert(data.result.transactions.isEmpty())
    }

    @Test
    fun `Should return failure`() {
        //Given
        val accountId = "1432"
        val returns = AccountMocks.createFailureRequestState()
        coEvery { repository.getAccountDetail(accountId) } returns returns
        //When
        sut.accountId = accountId
        //Then
        val data = sut.accountLiveData.getOrAwaitValue()
        assert(data is RequestState.Failure)
        assert((data as RequestState.Failure).error.message == "Error in getting account")
    }

}