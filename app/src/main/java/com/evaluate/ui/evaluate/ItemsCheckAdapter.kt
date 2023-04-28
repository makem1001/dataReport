package com.evaluate.ui.evaluate

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.evaluate.R

class ItemsCheckAdapter(private var items: List<ItemCheck>) : RecyclerView.Adapter<ItemsCheckAdapter.ViewHolder>() {


    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var descriptionView: TextView = itemView.findViewById(R.id.description)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_check_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.descriptionView.text = item.description
    }

    override fun getItemCount(): Int {
        return items.size
    }



    interface OnNextClickListener{
        fun onRefreshDatas()
    }
}
