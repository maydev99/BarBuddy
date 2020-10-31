package com.bombadu.barbuddy.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bombadu.barbuddy.R
import com.bombadu.barbuddy.local.LocalDrinkData
import com.bombadu.barbuddy.view_model.DrinkViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {


    private lateinit var drinkViewModel: DrinkViewModel
    private var mMenu: Menu? = null
    private lateinit var drinkName: String
    private lateinit var drinkIngredients: String
    private lateinit var drinkInstructions: String
    private lateinit var drinkImageUrl: String
    private var drinkFavorite: Boolean = false
    private lateinit var drinkId: String
    private var itemId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val actionBar = supportActionBar
        actionBar!!.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.gradient))
        drinkViewModel = ViewModelProvider(this).get(DrinkViewModel::class.java)

        val bundle = intent.extras
        drinkName = bundle!!.getString("drink_name").toString()
        drinkIngredients = bundle.getString("drink_ingredients").toString()
        drinkInstructions = bundle.getString("drink_instructions").toString()
        drinkImageUrl = bundle.getString("drink_image_url").toString()
        drinkFavorite = bundle.getBoolean("drink_favorite")
        drinkId = bundle.getString("drink_id").toString()
        itemId = bundle.getInt("item_id")


        val drinkImageView = findViewById<ImageView>(R.id.detail_image_view)

        Picasso.get().load(drinkImageUrl).into(drinkImageView)
        detail_drink_name_text_view.text = drinkName
        detail_ingredients_text_view.text = drinkIngredients
        detail_instructions_text_view.text = drinkInstructions
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        mMenu = menu
        if (drinkFavorite) {
            mMenu?.findItem(R.id.action_favorite)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
        } else {
            mMenu?.findItem(R.id.action_favorite)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show()
            }
        }

        when (item.itemId) {
            R.id.action_favorite -> {
                if (drinkFavorite) {
                    mMenu?.findItem(R.id.action_favorite)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
                    drinkFavorite = false
                    updateDB(drinkFavorite)
                } else {
                    mMenu?.findItem(R.id.action_favorite)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
                    drinkFavorite = true
                    updateDB(drinkFavorite)
                }
            }
        }


        return super.onOptionsItemSelected(item)

    }

    private fun updateDB(drinkFavorite: Boolean) {
        val updatedDrink = LocalDrinkData(drinkId,drinkImageUrl,
        drinkInstructions, drinkName, drinkIngredients, drinkFavorite)
        updatedDrink.id = itemId
        drinkViewModel.insertDrinkData(updatedDrink)


    }
}