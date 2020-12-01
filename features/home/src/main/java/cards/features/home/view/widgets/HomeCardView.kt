package cards.features.home.view.widgets

import android.content.Context
import android.view.LayoutInflater
import cards.features.home.databinding.HomeCardViewBinding
import cards.features.home.model.CardContent
import cards.features.home.model.Widget

class HomeCardView(context: Context): BaseWidget(context) {

    private lateinit var binding: HomeCardViewBinding

    override fun buildView(widget: Widget) {
        binding = HomeCardViewBinding.inflate(LayoutInflater.from(context), this, true)
        val cardContent = CardContent.fromMap(widget.content)
        with(binding) {
            cardName.text = cardContent.title
            cardNumber.text = cardContent.cardNumber
            actionButton.text = cardContent.button.title
            actionButton.setOnClickListener {
                cardContent.button.action?.intent(context)?.let { context.startActivity(it) }
            }
        }
    }

}