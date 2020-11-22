package cards.features.carddetails.networking

import androidx.lifecycle.LiveData
import cards.core.model.ApiResult
import cards.features.carddetails.model.CardDetails

interface CardRepositoryInterface {

    fun getLiveData(): LiveData<ApiResult<CardDetails>>
    fun getCardDetail(cardId: String)

}