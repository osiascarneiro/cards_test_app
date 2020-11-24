package cards.features.home.viewmodel

import cards.core.model.RequestState
import cards.core.test.base.BaseTest
import cards.core.test.rule.MainCoroutineRule
import cards.core.test.util.getOrAwaitValue
import cards.features.home.data.HomeRepositoryInterface
import cards.features.home.mock.HomeMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest: BaseTest() {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var sut: HomeViewModel
    private val repository = mockk<HomeRepositoryInterface>()

    @Test
    fun `Should return success`() {
        //Given
        val result = HomeMocks.createSuccessRequestState()
        coEvery { repository.getWidgets() } returns result
        //When
        sut = HomeViewModel(repository)
        //Then
        val liveDataResult = sut.widgetsLiveData.getOrAwaitValue()
        assert(liveDataResult is RequestState.Success)
        assert((liveDataResult as RequestState.Success).result.widgets.size == 11)
    }

    @Test
    fun `Should return failure`() {
        //Given
        val errorResult = HomeMocks.createFailureRequestState()
        coEvery { repository.getWidgets() } returns errorResult
        //When
        sut = HomeViewModel(repository)
        //Then
        val liveDataResult = sut.widgetsLiveData.getOrAwaitValue()
        assert(liveDataResult is RequestState.Failure)
        assert((liveDataResult as RequestState.Failure).error.message == "Error in getting widgets")
    }

}