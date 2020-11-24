package cards.features.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import cards.core.model.RequestState
import cards.features.home.model.Widget
import cards.features.home.model.WidgetList
import cards.features.home.model.WidgetType
import cards.features.home.data.HomeRepositoryInterface
import cards.core.test.util.MainCoroutineRule
import cards.core.test.util.getOrAwaitValue
import cards.features.home.mock.HomeMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

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