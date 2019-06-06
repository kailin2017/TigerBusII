package com.kailin.bus.util

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import java.lang.ref.WeakReference

class TabPager2Mediator(tabLayout: TabLayout, viewPager2: ViewPager2, callback: OnConfigureTabCallback) {

    init {

        tabLayout.addOnTabSelectedListener(OnTabSelectedListener(viewPager2))

        viewPager2.registerOnPageChangeCallback(OnPageChangeCallback(tabLayout))

        val adapter = viewPager2.adapter
        if (adapter != null) {
            for (position in 0 until adapter.itemCount) {
                val tab = tabLayout.newTab()
                callback.onConfigureTab(tab, position)
                tabLayout.addTab(tab, position == 0)
            }
        }
    }
}

interface OnConfigureTabCallback {

    fun onConfigureTab(tab: TabLayout.Tab, position: Int)
}

class OnPageChangeCallback(tabLayout: TabLayout) : ViewPager2.OnPageChangeCallback() {

    private val weakReference = WeakReference<TabLayout>(tabLayout)

    override fun onPageScrollStateChanged(state: Int) {
        super.onPageScrollStateChanged(state)
        val tabLayout = weakReference.get() ?: return

        if (state >= tabLayout.tabCount || state == tabLayout.selectedTabPosition)
            return

        tabLayout.getTabAt(state)!!.select()
    }

//    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
//    }
//
//    override fun onPageSelected(position: Int) {
//        super.onPageSelected(position)
//
//    }
}

class OnTabSelectedListener(private val viewPager2: ViewPager2) : TabLayout.OnTabSelectedListener {

    override fun onTabReselected(p0: TabLayout.Tab?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        viewPager2.currentItem = p0!!.position
    }
}