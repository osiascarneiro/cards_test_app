package cards.features.accountdetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cards.core.model.ApiResult
import cards.features.accountdetails.model.AccountDetail
import cards.features.accountdetails.networking.AccountRepositoryInterface
import kotlinx.coroutines.launch

class AccountViewModel(
    private val repository: AccountRepositoryInterface
): ViewModel() {

    val accountLiveData = MutableLiveData<ApiResult<AccountDetail>>()

    var accountId: String? = null
        set(value) {
            field = value
            value?.let{ getDetail(it) }
        }

    private fun getDetail(id: String) {
        viewModelScope.launch {
            accountLiveData.value = ApiResult.Loading(true)
            val result = repository.getAccountDetail(id)
            accountLiveData.value = ApiResult.Loading(false)
            accountLiveData.value = result
        }
    }

}