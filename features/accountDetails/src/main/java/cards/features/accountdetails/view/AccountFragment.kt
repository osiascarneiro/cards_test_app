package cards.features.accountdetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import cards.actions.Actions
import cards.core.model.ApiResult
import cards.features.accountdetails.R
import cards.features.accountdetails.model.Balance
import cards.features.accountdetails.view.adapter.TransactionsAdapter
import cards.features.accountdetails.viewmodel.AccountViewModel
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_account.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class AccountFragment : Fragment() {

    private val accountViewModel: AccountViewModel by viewModel()
    private val transactionsAdapter = TransactionsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        accountViewModel.accountLiveData.observe(this, Observer {
            when(it) {
                is ApiResult.Loading -> {
                    loadingBar.visibility = if(it.loading) View.VISIBLE else View.GONE
                }
                is ApiResult.Failure -> {
                    errorText.visibility = View.VISIBLE
                    errorText.text = it.error.message
                }
                is ApiResult.Success -> {
                    populateBalance(it.result.balance)
                    transactionsAdapter.transactions = it.result.transactions
                    transactionsAdapter.notifyDataSetChanged()
                }
            }
        })

        extractArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)
        view.transactions.adapter = transactionsAdapter
        return view
    }

    private fun populateBalance(balance: Balance) {
        labelBalance.text = balance.label
        valueBalance.text = balance.value
    }

    private fun extractArguments() {
        activity?.intent?.apply {
            val cardId = this.getStringExtra(Actions.ACCOUNT_ID_EXTRA)
            accountViewModel.accountId = cardId
        }
    }
}