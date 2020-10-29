package com.bombadu.barbuddy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.bombadu.barbuddy.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPagerViewWithFragments()

    }

    private fun initPagerViewWithFragments() {
        val viewPager2: ViewPager2 = findViewById(R.id.viewPager)
        val adapter = StateAdapter(supportFragmentManager, lifecycle)
        viewPager2.adapter = adapter

        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        val names: Array<String> = arrayOf("Searches", "Favorites")
        TabLayoutMediator(tabLayout, viewPager2) {tab, position ->
            tab.text = names[position]
        }.attach()
    }
}