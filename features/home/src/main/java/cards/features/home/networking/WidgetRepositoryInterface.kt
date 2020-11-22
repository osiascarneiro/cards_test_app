package cards.features.home.networking

import androidx.lifecycle.LiveData
import cards.core.model.ApiResult
import cards.features.home.model.WidgetList

interface WidgetRepositoryInterface {
    fun getWidgets(): LiveData<ApiResult<WidgetList>>
}