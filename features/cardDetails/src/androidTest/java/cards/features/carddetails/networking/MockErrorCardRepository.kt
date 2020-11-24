package cards.features.carddetails.networking

import cards.core.model.ApiResult
import cards.features.carddetails.model.CardDetails

class MockErrorCardRepository: CardRepositoryInterface {
    override suspend fun getCardDetail(cardId: String): ApiResult<CardDetails> {
        return ApiResult.Failure(Error("Error getting card details"))
    }
}