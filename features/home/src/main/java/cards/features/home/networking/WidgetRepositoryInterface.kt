package cards.features.home.networking

import cards.core.model.ApiResult
import cards.features.home.model.WidgetList

interface WidgetRepositoryInterface {
    suspend fun getWidgets(): ApiResult<WidgetList>
}