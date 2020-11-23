package cards.features.carddetails.networking

import cards.core.model.ApiResult
import cards.features.carddetails.model.CardDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardRepository(
    private val service: CardService,
    private val dispatcher: CoroutineDispatcher =  Dispatchers.IO
): CardRepositoryInterface {

    override suspend fun getCardDetail(cardId: String): ApiResult<CardDetails> = withContext(dispatcher) {
        val response = service.getCardDetail(cardId)
        if(response.code() != 200) { return@withContext ApiResult.Failure(Error(response.errorBody()?.string())) }
        else { response.body()?.let { return@withContext ApiResult.Success(it) } }
        ApiResult.Failure(Error("Erro desconhecido"))
    }

}