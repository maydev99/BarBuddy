package com.bombadu.barbuddy.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
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

class MainAdapter(context: Context) :
    ListAdapter<LocalDrinkData, MainAdapter.DrinkViewHolder>(ITEM_COMPARATOR) {

    private val mContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item, parent, false)
        return DrinkViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {

        holder.bind(getItem(position))
        val data = getItem(position)
        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putString("drink_name", data.drink_name)
            bundle.putString("drink_image_url", data.drink_image_url)
            bundle.putString("drink_ingredients", data.drink_ingredients)
            bundle.putString("drink_instructions", data.drink_instructions)
            bundle.putBoolean("drink_favorite", data.drink_favorite)
            bundle.putString("drink_id", data.drink_id)
            bundle.putInt("item_id", data.id)
            intent.putExtras(bundle)
            mContext.startActivity(intent)
        }

    }


    inner class DrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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