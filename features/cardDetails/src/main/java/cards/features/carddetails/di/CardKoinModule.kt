package cards.features.carddetails.di

import cards.core.di.RetrofitBuilder
import cards.features.carddetails.networking.CardRepository
import cards.features.carddetails.networking.CardRepositoryInterface
import cards.features.carddetails.networking.CardService
import cards.features.carddetails.viewmodel.CardViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.binds
import org.koin.dsl.module

val module = module {
    single { RetrofitBuilder.buildRetrofitService(CardService::class.java) }
    factory { CardRepository(get()) } binds arrayOf(CardRepositoryInterface::class)
    viewModel { CardViewModel(get()) }
}