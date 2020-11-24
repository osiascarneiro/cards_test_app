package cards.features.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import cards.core.model.ApiResult
import cards.features.home.model.Widget
import cards.features.home.model.WidgetList
import cards.features.home.model.WidgetType
import cards.features.home.networking.WidgetRepositoryInterface
import cards.core.test.util.MainCoroutineRule
import cards.core.test.util.getOrAwaitValue
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
    private val repository = mockk<WidgetRepositoryInterface>()

    @Test
    fun `Should return success`() {
        //Given
        val list = ArrayList<Widget>()
        for(i in 0..10) {
            val widget = Widget(WidgetType.HOME_CARD, emptyMap())
            list.add(widget)
        }
        val widgetList = WidgetList(list)
        val result = ApiResult.Success(widgetList)
        coEvery { repository.getWidgets() } returns result
        //When
        sut = HomeViewModel(repository)
        //Then
        val liveDataResult = sut.widgetsLiveData.getOrAwaitValue()
        assert(liveDataResult is ApiResult.Success)
        assert((liveDataResult as ApiResult.Success).result === widgetList)
    }

    @Test
    fun `Should return failure`() {
        //Given
        val errorResult = ApiResult.Failure<WidgetList>(Error("Error in getting widgets"))
        coEvery { repository.getWidgets() } returns errorResult
        //When
        sut = HomeViewModel(repository)
        //Then
        val liveDataResult = sut.widgetsLiveData.getOrAwaitValue()
        assert(liveDataResult is ApiResult.Failure)
        assert((liveDataResult as ApiResult.Failure).error.message == "Error in getting widgets")
    }

}