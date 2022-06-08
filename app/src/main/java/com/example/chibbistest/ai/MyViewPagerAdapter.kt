package com.example.chibbistest.ai

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chibbistest.MainActivity
import com.example.chibbistest.ai.hits.HitsFragment
import com.example.chibbistest.ai.restaurants.RestaurantsFragment
import com.example.chibbistest.ai.reviews.ReviewsFragment


public class MyViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RestaurantsFragment()
            1 -> HitsFragment()
            2 -> ReviewsFragment()
            else -> Fragment()
        }
    }
}

