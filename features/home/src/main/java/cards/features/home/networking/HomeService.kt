package cards.features.home.networking

import cards.features.home.model.WidgetList
import retrofit2.Call
import retrofit2.http.GET

interface HomeService {

    @GET("home")
    fun gethomeWidgets(): Call<WidgetList>

}