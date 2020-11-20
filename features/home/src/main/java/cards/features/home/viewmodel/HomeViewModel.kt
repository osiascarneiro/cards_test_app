package cards.features.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cards.features.home.model.WidgetList
import cards.features.home.networking.HomeService
import kotlinx.coroutines.launch

class HomeViewModel(
    private val service: HomeService
): ViewModel() {

    val widgetsLiveData = MutableLiveData<WidgetList>()

    fun getWidgets() {
        viewModelScope.launch {
            val widgets = service.gethomeWidgets()
            widgetsLiveData.value = widgets
        }
    }

}