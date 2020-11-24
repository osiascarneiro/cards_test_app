package cards.features.carddetails.data

import cards.core.model.RequestState
import cards.core.test.base.BaseTest
import cards.features.carddetails.mock.CardsMock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CardsRepositoryTest: BaseTest() {

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
        val returns = CardsMock.createSuccessResponse()
        coEvery { service.getCardDetail(cardId) } returns returns
        //When
        val data = runBlocking { sut.getCardDetail(cardId) }
        //Then
        assert(data is RequestState.Success)
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
        val returns = CardsMock.createFailureResponse()
        coEvery { service.getCardDetail(cardId) } returns returns
        //When
        val data = runBlocking { sut.getCardDetail(cardId) }
        //Then
        assert(data is RequestState.Failure)
        assert((data as RequestState.Failure).error.message == "Error in getting card")
    }

}