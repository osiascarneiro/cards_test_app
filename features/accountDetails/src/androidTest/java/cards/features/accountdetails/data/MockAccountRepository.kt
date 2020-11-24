package cards.features.accountdetails.data

import cards.core.model.RequestState
import cards.features.accountdetails.model.AccountDetail
import cards.features.accountdetails.model.Balance
import cards.features.accountdetails.model.Transaction

class MockAccountRepository: AccountRepositoryInterface {

    override suspend fun getAccountDetail(accountId: String): RequestState<AccountDetail> {
        val balance = Balance("Saldo disponível", "R$ 5.000,00")
        val list = ArrayList<Transaction>()
        for(i in 0..5) {
            val transaction = Transaction("Compra nº $i", "R$ $i,00", "Compra com cartão $i")
            list.add(transaction)
        }
        val accountDetail = AccountDetail(balance, list)
        return RequestState.Success(accountDetail)
    }

}