package cards.features.carddetails.data

import cards.features.carddetails.model.CardDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CardService {

    @GET("card/{cardId}")
    suspend fun getCardDetail(@Path("cardId") cardId: String): Response<CardDetails>

}