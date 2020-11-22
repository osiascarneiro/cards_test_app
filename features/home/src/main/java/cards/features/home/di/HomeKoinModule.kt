package cards.features.home.di

import cards.core.di.RetrofitBuilder
import cards.features.home.networking.HomeService
import cards.features.home.networking.WidgetRepository
import cards.features.home.networking.WidgetRepositoryInterface
import cards.features.home.viewmodel.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val module = module {
    single { RetrofitBuilder.buildRetrofitService(HomeService::class.java) }
    factory { WidgetRepository(get()) } bind WidgetRepositoryInterface::class
    viewModel { HomeViewModel(get()) }
}
