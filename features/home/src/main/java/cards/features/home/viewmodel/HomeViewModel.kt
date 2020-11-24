package cards.features.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cards.core.model.RequestState
import cards.features.home.model.WidgetList
import cards.features.home.data.HomeRepositoryInterface
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepositoryInterface
): ViewModel() {

    var widgetsLiveData = MutableLiveData<RequestState<WidgetList>>()

    init {
        getWidgets()
    }

    private fun getWidgets() {
        viewModelScope.launch {
            widgetsLiveData.value = RequestState.Loading(true)
            val response = repository.getWidgets()
            widgetsLiveData.value = RequestState.Loading(false)
            widgetsLiveData.value = response
        }
    }

}