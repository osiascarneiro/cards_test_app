package cards.features.carddetails.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import cards.core.model.ApiResult
import cards.features.carddetails.model.CardDetails
import cards.features.carddetails.networking.CardRepositoryInterface
import cards.core.test.util.MainCoroutineRule
import cards.core.test.util.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var sut: CardViewModel
    private val repository = mockk<CardRepositoryInterface>()

    @Before
    fun setup() {
        sut = CardViewModel(repository)
    }

    @Test
    fun `Should return success`() {
        //Given
        val cardId = "1432"
        val card = CardDetails("","","","","")
        val returns = ApiResult.Success(card)
        coEvery { repository.getCardDetail(cardId) } returns returns
        //When
        sut.cardId = cardId
        //Then
        val data = sut.cardLiveData.getOrAwaitValue()
        assert(data is ApiResult.Success)
        assert((data as ApiResult.Success).result === card)
    }

    @Test
    fun `Should return failure`() {
        //Given
        val cardId = "1432"
        val returns = ApiResult.Failure<CardDetails>(Error("Error in getting card"))
        coEvery { repository.getCardDetail(cardId) } returns returns
        //When
        sut.cardId = cardId
        //Then
        val data = sut.cardLiveData.getOrAwaitValue()
        assert(data is ApiResult.Failure)
        assert((data as ApiResult.Failure).error.message == "Error in getting card")
    }

}