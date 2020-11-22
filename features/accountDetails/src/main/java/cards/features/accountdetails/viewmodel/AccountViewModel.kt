package cards.features.accountdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cards.features.accountdetails.networking.AccountRepositoryInterface
import kotlinx.coroutines.launch

class AccountViewModel(
    private val repository: AccountRepositoryInterface
): ViewModel() {

    val accountLiveData = repository.getLiveData()
    var accountId: String? = null
        set(value) {
            field = value
            value?.let{ getDetail(it) }
        }

    private fun getDetail(id: String) {
        viewModelScope.launch { repository.getAccountDetail(id) }
    }

}