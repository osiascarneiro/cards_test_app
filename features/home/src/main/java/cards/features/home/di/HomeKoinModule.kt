package cards.features.home.di

import cards.features.home.networking.WidgetRepository
import cards.features.home.networking.WidgetRepositoryInterface
import cards.features.home.viewmodel.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.binds
import org.koin.dsl.module

val module = module {
    single { RetrofitBuilder.buildRetrofitService() }
    factory { WidgetRepository(get()) } binds arrayOf(WidgetRepositoryInterface::class)
    viewModel { HomeViewModel(get()) }
}
