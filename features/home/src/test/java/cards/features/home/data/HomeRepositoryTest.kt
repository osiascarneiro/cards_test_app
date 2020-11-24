package cards.features.home.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import cards.core.model.RequestState
import cards.features.home.mock.HomeMocks
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

class HomeRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var sut: HomeRepository
    private val service = mockk<HomeService>()

    @Before
    fun setup() {
        sut = HomeRepository(service)
    }

    @Test
    fun `Should return success`() {
        //Given
        val response = HomeMocks.createSuccessResponse()
        coEvery { service.gethomeWidgets() } returns response
        //When
        val result = runBlocking { sut.getWidgets() }
        //Then
        assert(result is RequestState.Success)
        assert((result as RequestState.Success).result.widgets.size == 11)
    }

    @Test
    fun `Should return failure`() {
        //Given
        val errorResponse = HomeMocks.createFailureResponse()
        coEvery { service.gethomeWidgets() } returns errorResponse
        //When
        val result = runBlocking { sut.getWidgets() }
        //Then
        assert(result is RequestState.Failure)
        assert((result as RequestState.Failure).error.message == "Error in getting widgets")
    }

}