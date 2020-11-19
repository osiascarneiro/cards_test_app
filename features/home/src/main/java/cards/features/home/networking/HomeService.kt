package cards.features.home.networking

import retrofit2.Call
import retrofit2.http.GET

interface HomeService {

    @GET("https://mpay-android-interview.herokuapp.com/android/interview/home")
    fun gethomeWidgets(): Call<Any>

}