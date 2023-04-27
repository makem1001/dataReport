package com.evaluate.ui.dashboard

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.evaluate.R

class ItemsAdapter(private var items: List<Item>,var itemClickListener:OnNextClickListener) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    private val TYPE_TITLE = 1
    private val TYPE_DESCRIPTION = 2
    private val TYPE_NEXT = 3


    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val descriptionView: TextView = itemView.findViewById(R.id.description)
    }

    class ViewHolder1(itemView: View):ViewHolder(itemView) {
        val titleView:TextView = itemView.findViewById(R.id.title)
    }
    class ViewHolder2(itemView: View):ViewHolder(itemView) {
        val descriptionView:TextView = itemView.findViewById(R.id.description)
    }
    class ViewHolder3(itemView: View):ViewHolder(itemView) {
        val next:TextView = itemView.findViewById(R.id.next)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutId = -1
        when(viewType) {
            TYPE_TITLE-> {
                layoutId = R.layout.item_title_layout
                val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
                return ViewHolder1(view)
            }

            TYPE_DESCRIPTION -> {
                layoutId = R.layout.item_layout
                val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
                return ViewHolder2(view)
            }

            TYPE_NEXT -> {
                layoutId = R.layout.item_next_layout
                val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
                return ViewHolder3(view)
            }

            else -> {
                throw IllegalArgumentException("Invalid view type")
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        when(holder) {
            is ViewHolder1 -> {
                holder.titleView.text = item.description
            }

            is ViewHolder2 -> {
                holder.descriptionView.text = item.description
            }

            is ViewHolder3 -> {
                holder.next.text = item.description
                holder.next.setOnClickListener(View.OnClickListener {
                    itemClickListener.onRefreshDatas()
                })
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> {
                TYPE_TITLE
            }
            items.size-1 -> {
                TYPE_NEXT
            }
            else -> {
                TYPE_DESCRIPTION
            }
        }
    }

    open fun setItems(datas:List<Item>) {
        this.items = datas
        notifyDataSetChanged()
    }

    open fun getItemStep():Int {
        if(items.isEmpty()) {
            return -1
        }
        return items[0].step
    }

    interface OnNextClickListener{
        fun onRefreshDatas()
    }
}
