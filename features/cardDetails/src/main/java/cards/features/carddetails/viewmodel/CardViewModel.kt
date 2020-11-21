package cards.features.carddetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cards.features.carddetails.model.CardDetails
import cards.features.carddetails.networking.CardRepositoryInterface

class CardViewModel(
    private val repository: CardRepositoryInterface
): ViewModel() {

    lateinit var cardLiveData: LiveData<CardDetails>
    var cardId: String? = null
        set(value) {
            field = value
            value?.let { cardLiveData = repository.getCardDetail(it) }
        }

}