package www.androiddarknessbot.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import www.androiddarknessbot.R
import www.androiddarknessbot.navigation.NavigationHolder
import javax.inject.Inject

/**
 * @author t.shkolnik
 */

class NavigationHolderImpl @Inject constructor(
    private val fragmentManager: FragmentManager
) : NavigationHolder {

    override fun addFragment(fragment: Fragment, oldTag: String, newFragmentTag: String) {
        fragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, fragment, newFragmentTag)
            .addToBackStack(oldTag)
            .commitAllowingStateLoss()
    }

    override fun replaceFragment(fragment: Fragment, oldTag: String, newFragmentTag: String) {
        if (fragmentManager.findFragmentByTag(oldTag) != null) {
            if (fragmentManager.backStackEntryCount > EMPTY_BACKSTACK_COUNT)
                fragmentManager.popBackStack()

            fragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, fragment, newFragmentTag)
                .commitAllowingStateLoss()
        }
    }

    override fun addFirstFragment(fragment: Fragment, newFragmentTag: String) {
        with(fragmentManager) {
            popBackStack()
            beginTransaction()
                .replace(R.id.main_fragment_container, fragment, newFragmentTag)
                .commitAllowingStateLoss()
        }
    }

    companion object {

        private const val EMPTY_BACKSTACK_COUNT = 0
    }
}
