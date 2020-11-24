package cards.features.home.di

import cards.features.home.data.MockErrorHomeRepository
import cards.features.home.data.MockHomeRepository
import cards.features.home.data.HomeRepositoryInterface
import cards.features.home.viewmodel.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val mockModule = module {
    viewModel { HomeViewModel(get()) }
    factory { MockHomeRepository() } bind HomeRepositoryInterface::class
}

val mockErrorModule = module {
    viewModel { HomeViewModel(get()) }
    factory { MockErrorHomeRepository() } bind HomeRepositoryInterface::class
}