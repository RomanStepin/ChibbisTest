package com.example.chibbistest

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.chibbistest.ai.MyViewPagerAdapter
import com.example.chibbistest.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import com.jakewharton.threetenabp.AndroidThreeTen


class MainActivity : FragmentActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var pagerAdapter: MyViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this);
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initMainViewPager()
        initMainBottomNavigationView()
    }

    private fun initMainViewPager() {
        pagerAdapter = MyViewPagerAdapter(this)
        binding.apply {
            mainViewPager.offscreenPageLimit = 2
            mainViewPager.adapter = pagerAdapter
            mainViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    when (position) {
                        0 -> mainBottomNavigationView.menu.findItem(R.id.restaurantsFragment).isChecked =
                            true
                        1 -> mainBottomNavigationView.menu.findItem(R.id.hitsFragment).isChecked =
                            true
                        2 -> mainBottomNavigationView.menu.findItem(R.id.reviewsFragment).isChecked =
                            true
                    }
                    super.onPageSelected(position)
                }
            })
        }
    }

    private fun initMainBottomNavigationView() {
        binding.mainBottomNavigationView.setOnItemSelectedListener(object :
            NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.restaurantsFragment -> binding.mainViewPager.currentItem = 0
                    R.id.hitsFragment -> binding.mainViewPager.currentItem = 1
                    R.id.reviewsFragment -> binding.mainViewPager.currentItem = 2
                    else -> return false
                }
                return true
            }
        })
    }


    override fun onBackPressed() {
        if (binding.mainViewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            binding.mainViewPager.currentItem = binding.mainViewPager.currentItem - 1
        }
    }


}
