package cards.features.home.data

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
        val response = webservice.gethomeWidgets()
        if(response.code() != 200) { return@withContext RequestState.Failure(Error(response.errorBody()?.string())) }
        else { response.body()?.let { return@withContext RequestState.Success(it) } }
        return@withContext RequestState.Failure(Error("Erro desconhecido"))
    }

}