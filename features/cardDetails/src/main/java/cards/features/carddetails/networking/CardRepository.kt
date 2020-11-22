package cards.features.carddetails.networking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cards.core.model.ApiResult
import cards.features.carddetails.model.CardDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardRepository(
    private val service: CardService
): CardRepositoryInterface {

    val data: LiveData<ApiResult<CardDetails>> = MutableLiveData()

    override fun getLiveData(): LiveData<ApiResult<CardDetails>> = data

    override fun getCardDetail(cardId: String) {
        (data as MutableLiveData).value = ApiResult.Loading(true)
        service.getCardDetail(cardId).enqueue(object: Callback<CardDetails> {
            override fun onResponse(call: Call<CardDetails>, response: Response<CardDetails>) {
                data.value = ApiResult.Loading(false)
                if(response.code() != 200) { data.value = ApiResult.Failure(Error(response.errorBody()?.string())) }
                else { response.body()?.let { data.value = ApiResult.Success(it) } }
            }

            override fun onFailure(call: Call<CardDetails>, t: Throwable) {
                data.value = ApiResult.Loading(false)
                data.value = ApiResult.Failure(Error(t.message))
            }
        })
    }

}