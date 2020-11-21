package cards.features.carddetails.di

import cards.features.carddetails.networking.CardService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    @JvmStatic
    private fun getBaseUrl() = "https://mpay-android-interview.herokuapp.com/android/interview/"

    @JvmStatic
    fun buildRetrofitService(): CardService {
        val retrofit = Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CardService::class.java)
    }

}