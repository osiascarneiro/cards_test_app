package cards.features.home.di

import cards.features.home.networking.HomeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    @JvmStatic
    fun getBaseUrl() = "https://mpay-android-interview.herokuapp.com/android/interview/"

    @JvmStatic
    fun buildRetrofitService(): HomeService {
        val retrofit = Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(HomeService::class.java)
    }

}