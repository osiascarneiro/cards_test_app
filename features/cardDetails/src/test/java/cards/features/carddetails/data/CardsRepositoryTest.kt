package cards.features.carddetails.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import cards.core.model.RequestState
import cards.features.carddetails.model.CardDetails
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class CardsRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var sut: CardRepository
    private val service = mockk<CardService>()

    @Before
    fun setup() {
        sut = CardRepository(service)
    }

    @Test
    fun `Should return success`() {
        //Given
        val cardId = "1432"
        val card = CardDetails("","","","","")
        val returns = Response.success(card)
        coEvery { service.getCardDetail(cardId) } returns returns
        //When
        val data = runBlocking { sut.getCardDetail(cardId) }
        //Then
        assert(data is RequestState.Success)
        assert((data as RequestState.Success).result === card)
    }

    @Test
    fun `Should return failure`() {
        //Given
        val cardId = "1432"
        val returns = Response.error<CardDetails>(404, ResponseBody.create(MediaType.get("application/json"), "Error in getting card"))
        coEvery { service.getCardDetail(cardId) } returns returns
        //When
        val data = runBlocking { sut.getCardDetail(cardId) }
        //Then
        assert(data is RequestState.Failure)
        assert((data as RequestState.Failure).error.message == "Error in getting card")
    }

}