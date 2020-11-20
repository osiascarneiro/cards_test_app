package cards.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cards.features.home.model.WidgetList
import cards.features.home.networking.WidgetRepository
import cards.features.home.networking.WidgetRepositoryInterface

class HomeViewModel(
    repository: WidgetRepositoryInterface
): ViewModel() {

    var widgetsLiveData = repository.getWidgets()

}