package www.androiddarknessbot.core.presentation

import androidx.fragment.app.Fragment

interface NavigationHolder {
    fun replaceFragment(fragment: Fragment, oldTag: String, newFragmentTag: String)
    fun addFragment(fragment: Fragment, oldTag: String, newFragmentTag: String)
}