package cards.features.carddetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cards.core.model.ApiResult
import cards.features.carddetails.model.CardDetails
import cards.features.carddetails.networking.CardRepositoryInterface
import kotlinx.coroutines.launch

class CardViewModel(
    private val repository: CardRepositoryInterface
): ViewModel() {

    val cardLiveData = MutableLiveData<ApiResult<CardDetails>>()

    var cardId: String? = null
        set(value) {
            field = value
            value?.let { getCardDetail(it) }
        }

    private fun getCardDetail(cardId: String) {
        viewModelScope.launch {
            cardLiveData.value = ApiResult.Loading(true)
            val response = repository.getCardDetail(cardId)
            cardLiveData.value = ApiResult.Loading(false)
            cardLiveData.value = response
        }
    }

}