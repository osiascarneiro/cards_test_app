package cards.features.home.data

import cards.core.data.safeAPICall
import cards.core.model.RequestState
import cards.features.home.model.WidgetList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(
    private val webservice: HomeService,
    private val dispatchers: CoroutineDispatcher = Dispatchers.IO
): HomeRepositoryInterface {

    override suspend fun getWidgets(): RequestState<WidgetList> = withContext(dispatchers) {
        return@withContext safeAPICall { webservice.gethomeWidgets() }
    }

}