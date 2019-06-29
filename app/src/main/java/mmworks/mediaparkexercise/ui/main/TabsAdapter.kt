package mmworks.mediaparkexercise.ui.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import mmworks.mediaparkexercise.R

private val TAB_TITLES = arrayOf(
    R.string.list_tab_title,
    R.string.maps_tab_title
)

class TabsAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> ListFragment()
        else -> MapsFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? = context.resources.getString(TAB_TITLES[position])
    override fun getCount(): Int = 2
}