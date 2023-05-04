package com.creativehazio.kraitor.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingViewPagerAdapter(
    fragmentList : ArrayList<Fragment>,
    fm : FragmentManager,
    lifecycle : Lifecycle
) : FragmentStateAdapter(fm,lifecycle) {

    private val _fragmentList = fragmentList

    override fun getItemCount(): Int {
        return _fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return _fragmentList.get(position)
    }
}