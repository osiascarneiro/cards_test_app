package cards.features.carddetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import cards.actions.Actions
import cards.core.model.RequestState
import cards.features.carddetails.R
import cards.features.carddetails.model.CardDetails
import cards.features.carddetails.viewmodel.CardViewModel
import kotlinx.android.synthetic.main.fragment_card.*
import org.koin.android.viewmodel.ext.android.viewModel

class CardFragment : Fragment() {

    private val cardViewModel: CardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cardViewModel.cardLiveData.observe(requireActivity(), {
            when(it) {
                is RequestState.Failure -> {
                    with(errorText) {
                        visibility = View.VISIBLE
                        text = it.error.message
                    }
                }
                is RequestState.Success -> {
                    populateCard(it.result)
                }
                is RequestState.Loading -> {
                    loadingBar.isVisible = it.loading
                }
            }
        })

        extractArguments()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    private fun extractArguments() {
        activity?.intent?.apply {
            val cardId = this.getStringExtra(Actions.CARD_ID_EXTRA)
            cardViewModel.cardId = cardId
        }
    }

    private fun populateCard(card: CardDetails) {
        cardNumber.text = card.cardNumber
        cardName.text = card.cardName
        getString(R.string.activity_title)
        cardExpiration.text = getString(R.string.expiration_date, card.expirationDate)
        availableLimit.text = getString(R.string.available_limit, card.availableLimit)
        totalLimit.text = getString(R.string.total_limit, card.totalLimit)
    }

}