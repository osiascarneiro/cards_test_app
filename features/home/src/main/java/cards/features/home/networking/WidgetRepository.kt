package cards.features.home.networking

import cards.core.model.ApiResult
import cards.features.home.model.WidgetList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WidgetRepository(
    private val webservice: HomeService,
    private val dispatchers: CoroutineDispatcher = Dispatchers.IO
): WidgetRepositoryInterface {

    override suspend fun getWidgets(): ApiResult<WidgetList> = withContext(dispatchers) {
        val response = webservice.gethomeWidgets()
        if(response.code() != 200) { return@withContext ApiResult.Failure(Error(response.errorBody()?.string())) }
        else { response.body()?.let { return@withContext ApiResult.Success(it) } }
        return@withContext ApiResult.Failure(Error("Erro desconhecido"))
    }

}