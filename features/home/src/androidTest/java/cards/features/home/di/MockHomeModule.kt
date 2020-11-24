package cards.features.home.di

import cards.features.home.networking.MockWidgetRepository
import cards.features.home.networking.WidgetRepositoryInterface
import cards.features.home.viewmodel.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val mockModule = module {
    viewModel { HomeViewModel(get()) }
    factory { MockWidgetRepository() } bind WidgetRepositoryInterface::class
}