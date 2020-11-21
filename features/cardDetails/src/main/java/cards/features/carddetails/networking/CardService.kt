package cards.features.carddetails.networking

import cards.features.carddetails.model.CardDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CardService {

    @GET("card/{cardId}")
    fun getCardDetail(@Path("cardId") cardId: String): Call<CardDetails>

}