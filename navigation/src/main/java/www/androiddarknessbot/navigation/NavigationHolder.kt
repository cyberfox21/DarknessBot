package www.androiddarknessbot.navigation

import androidx.fragment.app.Fragment

/**
 * @author t.shkolnik
 */
interface NavigationHolder {
    fun replaceFragment(fragment: Fragment, oldTag: String, newFragmentTag: String)
    fun addFragment(fragment: Fragment, oldTag: String, newFragmentTag: String)
    fun addFirstFragment(fragment: Fragment, newFragmentTag: String)
}
