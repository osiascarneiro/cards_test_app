package cards.features.carddetails.data

import cards.core.model.RequestState
import cards.features.carddetails.model.CardDetails

interface CardRepositoryInterface {

    suspend fun getCardDetail(cardId: String): RequestState<CardDetails>

}