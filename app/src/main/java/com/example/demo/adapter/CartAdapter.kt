package com.example.demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demo.databinding.EachRowBinding
import com.example.demo.models.Profile

class CartAdapter(
    private var results: List<Profile>,
    private val listener: OnItemClickListener,
    var context: Context
) : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {
    private lateinit var eachRowBinding: EachRowBinding

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

    override fun getItemCount(): Int = results.size

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
    }
}
