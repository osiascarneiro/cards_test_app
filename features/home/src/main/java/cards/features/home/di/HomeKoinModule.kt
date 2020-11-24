package cards.features.home.di

import cards.core.di.RetrofitBuilder
import cards.features.home.data.HomeService
import cards.features.home.data.HomeRepository
import cards.features.home.data.HomeRepositoryInterface
import cards.features.home.viewmodel.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val module = module {
    single { RetrofitBuilder.buildRetrofitService(HomeService::class.java) }
    factory { HomeRepository(get()) } bind HomeRepositoryInterface::class
    viewModel { HomeViewModel(get()) }
}
