package cards.features.accountdetails.di

import cards.features.accountdetails.networking.AccountRepositoryInterface
import cards.features.accountdetails.networking.MockAccountRepository
import cards.features.accountdetails.viewmodel.AccountViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val mockModule = module {
    viewModel { AccountViewModel(get()) }
    factory { MockAccountRepository() } bind AccountRepositoryInterface::class
}