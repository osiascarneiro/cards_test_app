package cards.features.home.networking

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import cards.core.model.ApiResult
import cards.features.home.model.Widget
import cards.features.home.model.WidgetList
import cards.features.home.model.WidgetType
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class WidgetRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var sut: WidgetRepository
    private val service = mockk<HomeService>()

    @Before
    fun setup() {
        sut = WidgetRepository(service)
    }

    @Test
    fun `Should return success`() {
        //Given
        val list = ArrayList<Widget>()
        for(i in 0..10) {
            val widget = Widget(WidgetType.HOME_CARD, emptyMap())
            list.add(widget)
        }
        val widgetList = WidgetList(list)
        val response = Response.success(widgetList)
        coEvery { service.gethomeWidgets() } returns response
        //When
        val result = runBlocking { sut.getWidgets() }
        //Then
        assert(result is ApiResult.Success)
        assert((result as ApiResult.Success).result === widgetList)
    }

    @Test
    fun `Should return failure`() {
        //Given
        val errorResponse = Response.error<WidgetList>(404, ResponseBody.create(MediaType.get("application/json"), "Error in getting widgets"))
        coEvery { service.gethomeWidgets() } returns errorResponse
        //When
        val result = runBlocking { sut.getWidgets() }
        //Then
        assert(result is ApiResult.Failure)
        assert((result as ApiResult.Failure).error.message == "Error in getting widgets")
    }

}