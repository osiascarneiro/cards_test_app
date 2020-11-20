package cards.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cards.features.home.model.WidgetList
import cards.features.home.networking.WidgetRepository

class HomeViewModel(
    repository: WidgetRepository
): ViewModel() {

    var widgetsLiveData: LiveData<WidgetList> = repository.getWidgets()

}