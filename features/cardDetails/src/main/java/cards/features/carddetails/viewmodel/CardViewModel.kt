package cards.features.carddetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cards.core.model.ApiResult
import cards.features.carddetails.model.CardDetails
import cards.features.carddetails.networking.CardRepositoryInterface

class CardViewModel(
    private val repository: CardRepositoryInterface
): ViewModel() {

    val cardLiveData: LiveData<ApiResult<CardDetails>> = repository.getLiveData()

    var cardId: String? = null
        set(value) {
            field = value
            value?.let { repository.getCardDetail(it) }
        }

}