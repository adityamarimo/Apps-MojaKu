package com.moja.mojaku.ui.detail

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.moja.mojaku.ui.detail.information.InformationFragment
import com.moja.mojaku.ui.detail.synopsis.SynopsisFragment

class TabDetailAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = SynopsisFragment()
            1 -> fragment = InformationFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }
}