package cards.features.carddetails.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import cards.core.model.RequestState
import cards.features.carddetails.model.CardDetails
import cards.features.carddetails.data.CardRepositoryInterface
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
        val returns = RequestState.Success(card)
        coEvery { repository.getCardDetail(cardId) } returns returns
        //When
        sut.cardId = cardId
        //Then
        val data = sut.cardLiveData.getOrAwaitValue()
        assert(data is RequestState.Success)
        assert((data as RequestState.Success).result === card)
    }

    @Test
    fun `Should return failure`() {
        //Given
        val cardId = "1432"
        val returns = RequestState.Failure<CardDetails>(Error("Error in getting card"))
        coEvery { repository.getCardDetail(cardId) } returns returns
        //When
        sut.cardId = cardId
        //Then
        val data = sut.cardLiveData.getOrAwaitValue()
        assert(data is RequestState.Failure)
        assert((data as RequestState.Failure).error.message == "Error in getting card")
    }

}