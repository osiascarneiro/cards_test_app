package cards.features.home.view.widgets

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.widget.FrameLayout
import cards.features.home.R
import cards.features.home.model.CardContent
import cards.features.home.model.Widget
import kotlinx.android.synthetic.main.home_card_view.view.*

class HomeCardView(widget: Widget, context: Context): BaseWidget(widget, context) {

    override fun buildView(widget: Widget) {
        inflate(context, R.layout.home_card_view, this)
        val cardContent = CardContent.fromMap(widget.content)
        this.cardName.text = cardContent.title
        this.cardNumber.text = cardContent.cardNumber
        actionButton.text = cardContent.button.title
        actionButton.setOnClickListener {
            cardContent.button.action?.intent(context)?.let { context.startActivity(it) }
        }
    }

}