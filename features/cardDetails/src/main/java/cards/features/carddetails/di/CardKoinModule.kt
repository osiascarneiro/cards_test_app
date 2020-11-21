package cards.features.carddetails.di

import cards.features.carddetails.networking.CardRepository
import cards.features.carddetails.networking.CardRepositoryInterface
import cards.features.carddetails.viewmodel.CardViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.binds
import org.koin.dsl.module

val module = module {
    single { RetrofitBuilder.buildRetrofitService() }
    factory { CardRepository(get()) } binds arrayOf(CardRepositoryInterface::class)
    viewModel { CardViewModel(get()) }
}