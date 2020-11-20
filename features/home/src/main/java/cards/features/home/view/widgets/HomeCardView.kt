package cards.features.home.view.widgets

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import cards.features.home.R
import cards.features.home.model.CardContent
import cards.features.home.model.Widget
import kotlinx.android.synthetic.main.home_card_view.view.*

class HomeCardView(val widget: Widget, context: Context): FrameLayout(context) {

    init {
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        buildView()
    }

    private fun buildView() {
        inflate(context, R.layout.home_card_view, this)
        val cardContent = CardContent.fromMap(widget.content)
        this.cardName.text = cardContent.title
        this.cardNumber.text = cardContent.cardNumber
        actionButton.text = cardContent.button.title
    }

}