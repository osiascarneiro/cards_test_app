package cards.features.carddetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cards.core.model.RequestState
import cards.features.carddetails.model.CardDetails
import cards.features.carddetails.data.CardRepositoryInterface
import kotlinx.coroutines.launch

class CardViewModel(
    private val repository: CardRepositoryInterface
): ViewModel() {

    val cardLiveData = MutableLiveData<RequestState<CardDetails>>()

    var cardId: String? = null
        set(value) {
            field = value
            value?.let { getCardDetail(it) }
        }

    private fun getCardDetail(cardId: String) {
        viewModelScope.launch {
            cardLiveData.value = RequestState.Loading(true)
            val response = repository.getCardDetail(cardId)
            cardLiveData.value = RequestState.Loading(false)
            cardLiveData.value = response
        }
    }

}