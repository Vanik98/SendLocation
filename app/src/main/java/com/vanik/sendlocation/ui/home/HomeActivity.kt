package com.vanik.sendlocation.ui.home

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.vanik.sendlocation.R
import com.vanik.sendlocation.ui.base.BaseActivity
import com.vanik.sendlocation.ui.home.fragments.FragmentAdapter


class HomeActivity : BaseActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var fragmentAdapter: FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        showMessage("HomeActivity")
        viewPager = findViewById(R.id.home_view_pager)
        tabLayout = findViewById(R.id.home_tab_layout)
        fragmentAdapter = FragmentAdapter(supportFragmentManager,lifecycle)
        viewPager.adapter = fragmentAdapter
        tabLayout.addTab(tabLayout.newTab().setText("Send"))
        tabLayout.addTab(tabLayout.newTab().setText("Message"))
        tabLayout.addTab(tabLayout.newTab().setText("Map"))
        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                print("")
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
               print("")
            }

        })
        viewPager.registerOnPageChangeCallback(object  : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }

    override fun setUpViews() {
        println()
    }
}