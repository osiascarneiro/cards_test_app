package cards.features.carddetails.di

import cards.features.carddetails.networking.CardRepositoryInterface
import cards.features.carddetails.networking.MockCardRepository
import cards.features.carddetails.networking.MockErrorCardRepository
import cards.features.carddetails.viewmodel.CardViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val mockModule = module {
    viewModel { CardViewModel(get()) }
    factory { MockCardRepository() } bind CardRepositoryInterface::class
}

val mockErrorModule = module {
    viewModel { CardViewModel(get()) }
    factory { MockErrorCardRepository() } bind CardRepositoryInterface::class
}