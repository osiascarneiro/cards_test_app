package cards.features.accountdetails.di

import cards.core.di.RetrofitBuilder
import cards.features.accountdetails.data.AccountRepository
import cards.features.accountdetails.data.AccountRepositoryInterface
import cards.features.accountdetails.data.AccountService
import cards.features.accountdetails.viewmodel.AccountViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val module = module {
    single { RetrofitBuilder.buildRetrofitService(AccountService::class.java) }
    factory { AccountRepository(get()) } bind AccountRepositoryInterface::class
    viewModel { AccountViewModel(get()) }
}