package cards.features.carddetails.networking

import cards.core.model.ApiResult
import cards.features.carddetails.model.CardDetails

class MockCardRepository: CardRepositoryInterface {

    override suspend fun getCardDetail(cardId: String): ApiResult<CardDetails> {
        val card = CardDetails("1234 5678 9012 3456",
                                 "Fulano de tal",
                              "12/99",
                               "R$ 1.000.000,00",
                                  "R$ 5.000.000,00")
        return ApiResult.Success(card)
    }

}