package com.hungrok.mygallery

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class MyPagerAdapter(fm: FragmentManager?): FragmentStatePagerAdapter(fm) {

    private val items = ArrayList<Fragment>()

    override fun getItem(position: Int): Fragment { // Fragment 생성 혹은 생성되어진 객체 를 반납
        return items[position]
    }

    override fun getCount(): Int { // How many Fragments
        return items.size
    }

    fun updateFragments(items : List<Fragment>){ // 추가된 메쏘드
        this.items.addAll(items)
    }
}