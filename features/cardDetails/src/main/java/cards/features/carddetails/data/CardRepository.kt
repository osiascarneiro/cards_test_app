package cards.features.carddetails.data

import cards.core.data.safeAPICall
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
        return@withContext safeAPICall { service.getCardDetail(cardId) }
    }

}