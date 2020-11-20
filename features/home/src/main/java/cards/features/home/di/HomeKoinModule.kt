package cards.features.home.di

import cards.features.home.networking.HomeService
import cards.features.home.viewmodel.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HomeKoinModule {

    @JvmStatic
    fun getBaseUrl() = "https://mpay-android-interview.herokuapp.com/android/interview/"

    @JvmStatic
    fun homeModule(): Module {
        return module {
            single { buildRetrofitService() }
            viewModel { HomeViewModel(get()) }
        }
    }

    @JvmStatic
    fun buildRetrofitService(): HomeService {
        val retrofit = Retrofit.Builder()
                            .baseUrl(getBaseUrl())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
        return retrofit.create(HomeService::class.java)
    }

}