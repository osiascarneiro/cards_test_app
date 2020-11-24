package cards.features.carddetails.data

import cards.core.model.RequestState
import cards.features.carddetails.model.CardDetails

class MockErrorCardRepository: CardRepositoryInterface {
    override suspend fun getCardDetail(cardId: String): RequestState<CardDetails> {
        return RequestState.Failure(Error("Error getting card details"))
    }
}