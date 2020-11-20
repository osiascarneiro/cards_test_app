package cards.features.home.networking

import androidx.lifecycle.LiveData
import cards.features.home.model.WidgetList

interface WidgetRepositoryInterface {
    fun getWidgets(): LiveData<WidgetList>
}