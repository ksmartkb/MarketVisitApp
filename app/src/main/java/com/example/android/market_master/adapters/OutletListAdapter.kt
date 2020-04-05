package com.example.android.market_master.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.android.market_master.R
import com.example.android.market_master.domain.Outlet
import kotlinx.android.synthetic.main.outlet_fragment_item_list.view.*

class OutletListAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK : DiffUtil.ItemCallback<Outlet> = object : DiffUtil.ItemCallback<Outlet>() {

        override fun areItemsTheSame(oldItem: Outlet, newItem: Outlet): Boolean {
            return oldItem.outlet_id == newItem.outlet_id
        }

        override fun areContentsTheSame(oldItem: Outlet, newItem: Outlet): Boolean {
            return  oldItem==newItem
        }

    }
    private val differ :AsyncListDiffer<Outlet> = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return OutletViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.outlet_fragment_item_list,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is OutletViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Outlet>) {
        differ.submitList(list)
    }

    class OutletViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Outlet) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(bindingAdapterPosition, item)
            }

            itemView.outlet_name.text = item.outletName

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Outlet)
    }
}