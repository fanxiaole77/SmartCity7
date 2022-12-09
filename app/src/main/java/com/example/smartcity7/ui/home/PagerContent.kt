package com.example.smartcity7.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.smartcity7.network.NewsType

class PagerAdapter(fm:FragmentManager,val list: List<NewsType.Data>):FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return NewListFragment.newInstance(list[position].id.toString(),"")
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position].name
    }

}