package cards.features.carddetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cards.actions.Actions
import cards.core.model.ApiResult
import cards.features.carddetails.R
import cards.features.carddetails.model.CardDetails
import cards.features.carddetails.viewmodel.CardViewModel
import kotlinx.android.synthetic.main.fragment_card.*
import org.koin.android.viewmodel.ext.android.viewModel

class CardFragment : Fragment() {

    private val homeViewModel: CardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel.cardLiveData.observe(requireActivity(), {
            when(it) {
                is ApiResult.Failure -> {
                    errorText.visibility = View.VISIBLE
                    errorText.text = it.error.message
                }
                is ApiResult.Success -> {
                    populateCard(it.result)
                }
                is ApiResult.Loading -> {
                    loadingBar.visibility = if(it.loading) View.VISIBLE else View.GONE
                }
            }
        })

        extractArguments()
    }

    private fun extractArguments() {
        activity?.intent?.apply {
            val cardId = this.getStringExtra(Actions.CARD_ID_EXTRA)
            homeViewModel.cardId = cardId
        }
    }

    private fun populateCard(card: CardDetails) {
        cardNumber.text = card.cardNumber
        cardName.text = card.cardName
        cardExpiration.text = "Expiration ${card.expirationDate}"
        availableLimit.text = "Limite disponível: ${card.availableLimit}"
        totalLimit.text = "Limite total: ${card.totalLimit}"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    companion object {
        @JvmStatic fun newInstance() = CardFragment()
    }

}