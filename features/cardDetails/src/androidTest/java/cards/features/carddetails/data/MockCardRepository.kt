package cards.features.carddetails.data

import cards.core.model.RequestState
import cards.features.carddetails.model.CardDetails

class MockCardRepository: CardRepositoryInterface {

    override suspend fun getCardDetail(cardId: String): RequestState<CardDetails> {
        val card = CardDetails("1234 5678 9012 3456",
                                 "Fulano de tal",
                              "12/99",
                               "R$ 1.000.000,00",
                                  "R$ 5.000.000,00")
        return RequestState.Success(card)
    }

}