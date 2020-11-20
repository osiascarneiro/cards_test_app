package cards.features.home.view.widgets

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import cards.features.home.R
import cards.features.home.model.AccountContent
import cards.features.home.model.CardContent
import cards.features.home.model.Widget
import kotlinx.android.synthetic.main.home_account_view.view.*

class HomeAccountView(widget: Widget, context: Context): BaseWidget(widget, context) {

    override fun buildView(widget: Widget) {
        inflate(context, R.layout.home_account_view, this)
        val account = AccountContent.fromMap(widget.content)
        this.accountTitle.text = account.title
        this.accountLabel.text = account.balance.label
        this.accountBalance.text = account.balance.value
        actionButton.text = account.button.title
        actionButton.setOnClickListener {
            account.button.action?.intent(context)?.let { context.startActivity(it) }
        }
    }

}