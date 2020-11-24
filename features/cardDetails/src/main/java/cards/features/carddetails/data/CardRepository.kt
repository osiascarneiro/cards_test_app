package cards.features.carddetails.data

import cards.core.model.RequestState
import cards.features.carddetails.model.CardDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardRepository(
    private val service: CardService,
    private val dispatcher: CoroutineDispatcher =  Dispatchers.IO
): CardRepositoryInterface {

    override suspend fun getCardDetail(cardId: String): RequestState<CardDetails> = withContext(dispatcher) {
        val response = service.getCardDetail(cardId)
        if(response.code() != 200) { return@withContext RequestState.Failure(Error(response.errorBody()?.string())) }
        else { response.body()?.let { return@withContext RequestState.Success(it) } }
        RequestState.Failure(Error("Erro desconhecido"))
    }

}