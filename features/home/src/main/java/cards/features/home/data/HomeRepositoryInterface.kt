package cards.features.home.data

import cards.core.model.RequestState
import cards.features.home.model.WidgetList

interface HomeRepositoryInterface {
    suspend fun getWidgets(): RequestState<WidgetList>
}