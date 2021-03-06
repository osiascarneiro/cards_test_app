package cards.features.carddetails.viewmodel

import cards.core.model.RequestState
import cards.core.test.base.BaseTest
import cards.core.test.rule.MainCoroutineRule
import cards.core.test.util.getOrAwaitValue
import cards.features.carddetails.data.CardRepositoryInterface
import cards.features.carddetails.mock.CardsMock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardViewModelTest: BaseTest() {

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
        val returns = CardsMock.createSuccessRequestState()
        coEvery { repository.getCardDetail(cardId) } returns returns
        //When
        sut.cardId = cardId
        //Then
        val data = sut.cardLiveData.getOrAwaitValue()
        assert((data as RequestState.Success).result.cardNumber == "431")
        assert(data.result.cardName == "asdas")
        assert(data.result.availableLimit == "123,00")
        assert(data.result.totalLimit == "200,00")
        assert(data.result.expirationDate == "11/11")
    }

    @Test
    fun `Should return failure`() {
        //Given
        val cardId = "1432"
        val returns = CardsMock.createFailureRequestState()
        coEvery { repository.getCardDetail(cardId) } returns returns
        //When
        sut.cardId = cardId
        //Then
        val data = sut.cardLiveData.getOrAwaitValue()
        assert(data is RequestState.Failure)
        assert((data as RequestState.Failure).error.message == "Error in getting card")
    }

}