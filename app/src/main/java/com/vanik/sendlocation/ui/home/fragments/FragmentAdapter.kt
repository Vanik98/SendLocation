package com.vanik.sendlocation.ui.home.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vanik.sendlocation.ui.home.fragments.map.MapFragment
import com.vanik.sendlocation.ui.home.fragments.messages.MessageFragment
import com.vanik.sendlocation.ui.home.fragments.send.SendFragment


class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SendFragment()
            1 -> MessageFragment()
            2 -> MapFragment()
            else -> {
                SendFragment()
            }
        }
    }

}