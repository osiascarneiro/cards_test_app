package cards.features.accountdetails.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cards.features.accountdetails.databinding.FragmentAccountTransactionItemBinding
import cards.features.accountdetails.model.Transaction

class TransactionsAdapter(
    var transactions: List<Transaction> = emptyList()
): RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder>() {

    private var binding: FragmentAccountTransactionItemBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        binding = FragmentAccountTransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.user.text = transaction.description
        holder.label.text = transaction.label
        holder.value.text = transaction.value
    }

    override fun getItemCount(): Int = transactions.size

    inner class TransactionViewHolder(binding: FragmentAccountTransactionItemBinding): RecyclerView.ViewHolder(binding.root) {
        val user: TextView = binding.transactionUser
        val label: TextView = binding.transactionLabel
        val value: TextView = binding.transactionValue
    }

}