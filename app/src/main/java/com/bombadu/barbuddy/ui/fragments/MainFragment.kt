package com.bombadu.barbuddy.ui.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bombadu.barbuddy.R
import com.bombadu.barbuddy.ui.MainAdapter
import com.bombadu.barbuddy.view_model.DrinkViewModel
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var drinkViewModel: DrinkViewModel
    private lateinit var adapter: MainAdapter
    private val dNameList = mutableListOf<String>()
    private var isOrientationLandscape = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkOrientation()
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
        if (isOrientationLandscape) {
            val manager = GridLayoutManager(this.context, 4)
            recycler_view.layoutManager = manager
        } else {
            val manager = GridLayoutManager(this.context, 2)
            recycler_view.layoutManager = manager
        }


        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter
        this.drinkViewModel = ViewModelProvider(this).get(DrinkViewModel::class.java)
        drinkViewModel.getAllMyDrinks().observe(viewLifecycleOwner, { allDrinks ->
            allDrinks?.let { adapter.submitList(it) }
        })

        adapter.registerAdapterDataObserver(object :
            RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                recycler_view.scrollToPosition(positionStart)
            }
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
                    val position = dNameList.indexOf(query)
                    println(position)
                    if (position == -1) {
                        drinkViewModel.getDrinkDataFromNetwork(query)
                    } else {
                        recycler_view.scrollToPosition(position)
                    }

                    searchView.clearFocus()
                }

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })
    }

    private fun checkOrientation() {
        val orientation = this.resources.configuration.orientation
        isOrientationLandscape = orientation != Configuration.ORIENTATION_PORTRAIT
    }


}