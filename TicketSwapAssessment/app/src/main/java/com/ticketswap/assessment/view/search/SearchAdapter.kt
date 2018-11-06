package com.ticketswap.assessment.view.search

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ticketswap.assessment.R
import com.ticketswap.assessment.view.image.ImageLoader

class SearchAdapter(val imageLoader: ImageLoader, val placeHolderResId: Int,
                    val itemClickListener: (SearchAdapterItem) -> Unit) :
        RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private val items: MutableList<SearchAdapterItem> = mutableListOf()

    fun updateItems(items: List<SearchAdapterItem>) {
        DiffUtil.calculateDiff(SearchDiff(this.items, items)).also {
            this.items.apply {
                clear()
                addAll(items)
            }
        }.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(itemView: ViewGroup, position: Int): SearchViewHolder =
            SearchViewHolder(
                    LayoutInflater.from(itemView.context)
                            .inflate(R.layout.item_search, itemView, false)
            )

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(viewHolder: SearchViewHolder, position: Int) {
        with(items[position]) {
            viewHolder.name.text = name
            imageLoader.load(image, placeHolderResId, viewHolder.image)
            viewHolder.type.setImageResource(when (type) {
                SearchItemType.LOCAL -> R.drawable.ic_insert_drive_file
                SearchItemType.NETWORK -> R.drawable.ic_cloud
            })
        }
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { itemClickListener(items[adapterPosition]) }
        }
        val image = itemView.findViewById<ImageView>(R.id.image_view_search_item_image)
        val type = itemView.findViewById<ImageView>(R.id.image_view_search_item_type)
        val name = itemView.findViewById<TextView>(R.id.text_view_search_item_name)
    }

    inner class SearchDiff(val oldOnes: List<SearchAdapterItem>,
                           val newOnes: List<SearchAdapterItem>) : DiffUtil.Callback() {
        override fun areItemsTheSame(p0: Int, p1: Int): Boolean = oldOnes[p0].name == newOnes[p1].name &&
                oldOnes[p0].type == newOnes[p1].type

        override fun getOldListSize(): Int = oldOnes.count()

        override fun getNewListSize(): Int = newOnes.count()

        override fun areContentsTheSame(p0: Int, p1: Int): Boolean = oldOnes[p0].name == newOnes[p1].name
    }
}
