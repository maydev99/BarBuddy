package com.bombadu.barbuddy.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bombadu.barbuddy.R
import com.bombadu.barbuddy.local.LocalDrinkData
import com.bombadu.barbuddy.ui.MainAdapter
import com.bombadu.barbuddy.view_model.DrinkViewModel
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(R.layout.fragment_main) {



    private lateinit var drinkViewModel: DrinkViewModel
    private lateinit var adapter: MainAdapter





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MainAdapter(view.context)
        getTheData()
        setHasOptionsMenu(true)

    }

    private fun getTheData() {
        drinkViewModel = ViewModelProvider(this).get(DrinkViewModel::class.java)
        drinkViewModel.getAllMyDrinks().observe(viewLifecycleOwner,
            { list ->
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





        /*adapter.onItemClick = { pos, _ ->
            Log.d("CLICK2", "CLICK2")
            val myItem = adapter.getItemAt(pos)
            println("Drink id: $myItem.drink_name")
            val intent = Intent(this.context, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putString("drink_name", myItem!!.drink_name)
            bundle.putString("drink_ingredients", myItem.drink_ingredients)
            bundle.putString("drink_instructions", myItem.drink_instructions)
            bundle.putString("drink_image_url", myItem.drink_image_url)
            bundle.putString("drink_id", myItem.drink_id)
            intent.putExtras(bundle)
            startActivity(intent)

        }*/
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