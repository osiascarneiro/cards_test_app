package cards.features.home.networking

import cards.core.model.ApiResult
import cards.features.home.model.WidgetList

class MockErrorWidgetRepository: WidgetRepositoryInterface {

    override suspend fun getWidgets(): ApiResult<WidgetList> {
        return ApiResult.Failure(Error("Error in getting widgets"))
    }

}