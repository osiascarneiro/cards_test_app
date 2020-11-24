package cards.features.accountdetails.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import cards.core.model.ApiResult
import cards.features.accountdetails.model.AccountDetail
import cards.features.accountdetails.model.Balance
import cards.features.accountdetails.networking.AccountRepositoryInterface
import cards.core.test.util.MainCoroutineRule
import cards.core.test.util.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AccountViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

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
        val account = AccountDetail(Balance("", ""), emptyList())
        val returns = ApiResult.Success(account)
        coEvery { repository.getAccountDetail(accountId) } returns returns
        //When
        sut.accountId = accountId
        //Then
        val data = sut.accountLiveData.getOrAwaitValue()
        assert(data is ApiResult.Success)
        assert((data as ApiResult.Success).result === account)
    }

    @Test
    fun `Should return failure`() {
        //Given
        val accountId = "1432"
        val returns = ApiResult.Failure<AccountDetail>( Error("Error in getting account"))
        coEvery { repository.getAccountDetail(accountId) } returns returns
        //When
        sut.accountId = accountId
        //Then
        val data = sut.accountLiveData.getOrAwaitValue()
        assert(data is ApiResult.Failure)
        assert((data as ApiResult.Failure).error.message == "Error in getting account")
    }

}