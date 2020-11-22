package cards.features.home.networking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cards.core.model.ApiResult
import cards.features.home.model.WidgetList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WidgetRepository(
    private val webservice: HomeService
): WidgetRepositoryInterface {

    override fun getWidgets(): LiveData<ApiResult<WidgetList>> {
        val data = MutableLiveData<ApiResult<WidgetList>>()
        webservice.gethomeWidgets().enqueue(object: Callback<WidgetList> {
            override fun onResponse(call: Call<WidgetList>, response: Response<WidgetList>) {
                data.value = ApiResult.Loading(false)
                if(response.code() != 200) { data.value = ApiResult.Failure(Error(response.errorBody().toString())) }
                else { response.body()?.let { data.value = ApiResult.Success(it) } }
            }

            override fun onFailure(call: Call<WidgetList>, t: Throwable) {
                (data as MutableLiveData).value = ApiResult.Loading(false)
                data.value = ApiResult.Failure(Error(t.message))
            }
        })
        data.value = ApiResult.Loading(true)
        return data
    }

}