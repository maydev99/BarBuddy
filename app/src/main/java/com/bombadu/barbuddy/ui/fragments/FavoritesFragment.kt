package com.bombadu.barbuddy.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bombadu.barbuddy.R
import com.bombadu.barbuddy.ui.MainAdapter
import com.bombadu.barbuddy.view_model.DrinkViewModel
import kotlinx.android.synthetic.main.fragment_favorites.*


class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private lateinit var drinkViewModel: DrinkViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MainAdapter(view.context)
        getTheData()
        //setHasOptionsMenu(true)


    }

    private fun getTheData() {
        drinkViewModel = ViewModelProvider(this).get(DrinkViewModel::class.java)
        drinkViewModel.getFavoriteDrinks().observe(viewLifecycleOwner,
            { list ->
                list?.let {
                    adapter.submitList(it)

                }
            })

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val manager = GridLayoutManager(this.context, 2)
        favorite_recycler_view.layoutManager = manager
        favorite_recycler_view.setHasFixedSize(true)
        favorite_recycler_view.adapter = adapter
        this.drinkViewModel = ViewModelProvider(this).get(DrinkViewModel::class.java)
        drinkViewModel.getFavoriteDrinks().observe(viewLifecycleOwner, { allDrinks ->
            allDrinks?.let { adapter.submitList(it) }
        })

    }


}