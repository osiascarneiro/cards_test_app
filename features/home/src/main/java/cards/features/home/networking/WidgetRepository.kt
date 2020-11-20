package cards.features.home.networking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cards.features.home.model.WidgetList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WidgetRepository(
    private val webservice: HomeService
): WidgetRepositoryInterface {

    override fun getWidgets(): LiveData<WidgetList> {
        val data = MutableLiveData<WidgetList>()
        webservice.gethomeWidgets().enqueue(object: Callback<WidgetList> {
            override fun onResponse(call: Call<WidgetList>, response: Response<WidgetList>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<WidgetList>, t: Throwable) {
                //TODO: Error handling
                data.value = WidgetList(emptyList())
            }
        })
        return data
    }

}