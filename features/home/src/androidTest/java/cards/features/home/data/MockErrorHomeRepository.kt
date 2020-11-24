package cards.features.home.data

import cards.core.model.RequestState
import cards.features.home.model.WidgetList

class MockErrorHomeRepository: HomeRepositoryInterface {

    override suspend fun getWidgets(): RequestState<WidgetList> {
        return RequestState.Failure(Error("Error in getting widgets"))
    }

}