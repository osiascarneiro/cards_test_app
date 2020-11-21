package cards.features.carddetails.networking

import androidx.lifecycle.LiveData
import cards.features.carddetails.model.CardDetails

interface CardRepositoryInterface {

    fun getCardDetail(cardId: String): LiveData<CardDetails>

}