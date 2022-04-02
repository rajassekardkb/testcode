package com.example.demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demo.databinding.EachRowBinding
import com.example.demo.models.Profile

class CartAdapter(
    private var results: List<Profile>,
    private val listener: OnItemClickListener,
    var context: Context
) : RecyclerView.Adapter<CartAdapter.MyViewHolder>(), Filterable {
    private lateinit var eachRowBinding: EachRowBinding
    var resultsFilterList = ArrayList<Profile>()
    // exampleListFull . exampleList

    init {
        resultsFilterList = results as ArrayList<Profile>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        eachRowBinding = EachRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(eachRowBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = results[position]
        holder.eachRowBinding.expenseResponse = data
        holder.eachRowBinding.executePendingBindings()
        holder.bind(data, listener, context)
    }

    override fun getItemCount(): Int = resultsFilterList.size


    class MyViewHolder(var eachRowBinding: EachRowBinding) :
        RecyclerView.ViewHolder(eachRowBinding.root) {
        fun bind(
            data: Profile,
            listener: OnItemClickListener,
            context: Context
        ) {

            Glide.with(context)
                .load(data.profile_image)
                .into(eachRowBinding.imgReceipt)

            listener.onItemText(eachRowBinding.rtxCompanyName, data.id)

            eachRowBinding.cvExpenseList.setOnClickListener {
                listener.onItemClicked(data)
            }


        }
    }

    interface OnItemClickListener {
        fun onItemClicked(data: Profile)
        fun onItemText(data: TextView, id: String)
    }

    fun setData(expensesResponse: List<Profile>) {
        this.results = expensesResponse
        resultsFilterList = results as ArrayList<Profile>

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                resultsFilterList = if (charSearch.isEmpty()) {
                    results as ArrayList<Profile>
                } else {
                    val resultList = ArrayList<Profile>()
                    for (row in results) {
                        if (row.name.toLowerCase().contains(
                                constraint.toString().toLowerCase()
                            ) || row.email.toLowerCase()
                                .contains(constraint.toString().toLowerCase())
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = resultsFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                resultsFilterList = results?.values as ArrayList<Profile>
                notifyDataSetChanged()
            }
        }
    }
}
