package cards.features.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cards.core.model.ApiResult
import cards.features.home.model.WidgetList
import cards.features.home.networking.WidgetRepositoryInterface
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: WidgetRepositoryInterface
): ViewModel() {

    var widgetsLiveData = MutableLiveData<ApiResult<WidgetList>>()

    init {
        getWidgets()
    }

    private fun getWidgets() {
        viewModelScope.launch {
            widgetsLiveData.value = ApiResult.Loading(true)
            val response = repository.getWidgets()
            widgetsLiveData.value = ApiResult.Loading(false)
            widgetsLiveData.value = response
        }
    }

}