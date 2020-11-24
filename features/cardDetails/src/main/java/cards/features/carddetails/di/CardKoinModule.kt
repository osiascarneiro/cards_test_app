package cards.features.carddetails.di

import cards.core.di.RetrofitBuilder
import cards.features.carddetails.data.CardRepository
import cards.features.carddetails.data.CardRepositoryInterface
import cards.features.carddetails.data.CardService
import cards.features.carddetails.viewmodel.CardViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val module = module {
    single { RetrofitBuilder.buildRetrofitService(CardService::class.java) }
    factory { CardRepository(get()) } bind CardRepositoryInterface::class
    viewModel { CardViewModel(get()) }
}