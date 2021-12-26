package www.androiddarknessbot.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import www.androiddarknessbot.R
import www.androiddarknessbot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationHolder {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun addFragment(fragment: Fragment, oldTag: String, newFragmentTag: String) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(oldTag)
            .replace(R.id.main_fragment_container, fragment, newFragmentTag)
            .commit()
    }

    override fun replaceFragment(fragment: Fragment, oldTag: String, newFragmentTag: String) {
        if (supportFragmentManager.findFragmentByTag(oldTag) != null) {
            if (supportFragmentManager.backStackEntryCount > EMPTY_BACKSTACK_COUNT)
                supportFragmentManager.popBackStack()

            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, fragment, newFragmentTag)
                .commit()
        }
    }

    companion object {
        private const val EMPTY_BACKSTACK_COUNT = 0
    }

}