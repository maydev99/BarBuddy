package com.bombadu.barbuddy.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bombadu.barbuddy.R
import com.bombadu.barbuddy.local.LocalDrinkData
import com.squareup.picasso.Picasso

class MainAdapter : ListAdapter<LocalDrinkData, MainAdapter.DrinkViewHolder>(ITEM_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item, parent, false)
        return DrinkViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }


    class DrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var drinkImageView: ImageView? = itemView.findViewById(R.id.item_image_view)
        private var drinkNameTextView: TextView? = itemView.findViewById(R.id.item_textView)


        fun bind(localDrinkData: LocalDrinkData) {
            Picasso.get().load(localDrinkData.drink_image_url).into(drinkImageView)
            drinkNameTextView!!.text = localDrinkData.drink_name
        }
    }





    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<LocalDrinkData>() {
            override fun areItemsTheSame(oldItem: LocalDrinkData, newItem: LocalDrinkData) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: LocalDrinkData, newItem: LocalDrinkData) =
                oldItem.drink_id == newItem.drink_id
        }
    }



}