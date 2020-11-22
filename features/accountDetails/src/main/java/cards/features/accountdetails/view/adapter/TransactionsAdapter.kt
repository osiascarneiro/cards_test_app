package cards.features.accountdetails.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cards.features.accountdetails.R
import cards.features.accountdetails.model.Transaction
import kotlinx.android.synthetic.main.fragment_account_transaction_item.view.*

class TransactionsAdapter(
    var transactions: List<Transaction> = emptyList()
): RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_account_transaction_item, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.user?.text = transaction.description
        holder.label?.text = transaction.label
        holder.value?.text = transaction.value
    }

    override fun getItemCount(): Int = transactions.size

    inner class TransactionViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val user: TextView? = view.transactionUser
        val label: TextView? = view.transactionLabel
        val value: TextView? = view.transactionValue
    }

}