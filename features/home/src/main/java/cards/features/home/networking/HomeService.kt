package cards.features.home.networking

import cards.features.home.model.WidgetList
import retrofit2.Response
import retrofit2.http.GET

interface HomeService {

    @GET("home")
    suspend fun gethomeWidgets(): Response<WidgetList>

}