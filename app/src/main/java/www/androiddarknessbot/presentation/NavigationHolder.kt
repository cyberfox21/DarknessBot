package www.androiddarknessbot.presentation

import androidx.fragment.app.Fragment

interface NavigationHolder {
    fun replaceFragment(fragment: Fragment, oldTag: String, newFragmentTag: String)
    fun addFragment(fragment: Fragment, oldTag: String, newFragmentTag: String)
}