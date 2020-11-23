package cards.features.carddetails.networking

import cards.core.model.ApiResult
import cards.features.carddetails.model.CardDetails

interface CardRepositoryInterface {

    suspend fun getCardDetail(cardId: String): ApiResult<CardDetails>

}