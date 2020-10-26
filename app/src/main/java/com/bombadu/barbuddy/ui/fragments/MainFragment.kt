package com.bombadu.barbuddy.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bombadu.barbuddy.R
import com.bombadu.barbuddy.ui.MainAdapter
import com.bombadu.barbuddy.view_model.DrinkViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var drinkViewModel: DrinkViewModel
    private val adapter = MainAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getTheData()
        setHasOptionsMenu(true)
    }

    private fun getTheData() {
        drinkViewModel = ViewModelProvider(this).get(DrinkViewModel::class.java)
        /*drinkViewModel.getAllMyDrinks().observe(viewLifecycleOwner,
            { list ->
                list?.let {
                    Log.d("DATA", it.toString())
                    adapter.submitList(it)


                }
            })*/

        drinkViewModel.getAllMyDrinks().observe(viewLifecycleOwner,
            Observer { list ->
                list?.let {
                    adapter.submitList(it)

                }
            })

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val manager = GridLayoutManager(this.context, 2)
        recycler_view.layoutManager = manager
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter
        this.drinkViewModel = ViewModelProvider(this).get(DrinkViewModel::class.java)
        drinkViewModel.getAllMyDrinks().observe(viewLifecycleOwner, { allDrinks ->
            allDrinks?.let { adapter.submitList(it) }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    drinkViewModel.getDrinkDataFromNetwork(query)
                    searchView.clearFocus()
                }

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })
    }

}