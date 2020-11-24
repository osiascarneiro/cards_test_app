package cards.core.di

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    @JvmStatic
    fun getBaseUrl() = "https://mpay-android-interview.herokuapp.com/android/interview/"

    @JvmStatic
    fun <T> buildRetrofitService(service: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(service)
    }

}