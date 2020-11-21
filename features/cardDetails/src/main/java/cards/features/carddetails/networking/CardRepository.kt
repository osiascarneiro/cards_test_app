package cards.features.carddetails.networking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cards.features.carddetails.model.CardDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardRepository(
    private val service: CardService
): CardRepositoryInterface {

    override fun getCardDetail(cardId: String): LiveData<CardDetails> {
        val data = MutableLiveData<CardDetails>()
        service.getCardDetail(cardId).enqueue(object: Callback<CardDetails> {
            override fun onResponse(call: Call<CardDetails>, response: Response<CardDetails>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<CardDetails>, t: Throwable) {
                //TODO: Error handling
                data.value = CardDetails("", "", "", "", "")
            }
        })
        return data
    }

}