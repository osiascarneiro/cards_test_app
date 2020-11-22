package cards.features.accountdetails.viewmodel

import androidx.lifecycle.ViewModel
import cards.features.accountdetails.networking.AccountRepositoryInterface

class AccountViewModel(
    private val repository: AccountRepositoryInterface
): ViewModel() {

    val accountLiveData = repository.getLiveData()
    var accountId: String? = null
        set(value) {
            field = value
            value?.let{ repository.getAccountDetail(it) }
        }
}