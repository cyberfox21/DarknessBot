package www.androiddarknessbot.core.presentation

import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import www.androiddarknessbot.R
import www.androiddarknessbot.dashboard.presentation.DashboardFragment
import www.androiddarknessbot.databinding.ActivityMainBinding
import www.androiddarknessbot.scan.domain.entity.BotBluetoothDevice
import www.androiddarknessbot.scan.presentation.ScanFragment

class MainActivity : AppCompatActivity(), NavigationHolder {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let { parseArguments(it) }
        setContentView(binding.root)
    }

    private fun parseArguments(savedInstanceState: Bundle) {
        if (savedInstanceState.containsKey(OPEN_FRAGMENT))
            processOpenFragments(savedInstanceState)

    }

    private fun processOpenFragments(savedInstanceState: Bundle) =
        when (savedInstanceState[OPEN_FRAGMENT] as Fragments) {
            Fragments.SCAN -> addFirstFragment(ScanFragment.newInstance(), ScanFragment.TAG)
            Fragments.DASHBOARD -> {
                if (savedInstanceState.containsKey(DEVICE) && savedInstanceState[DEVICE] != null) {
                    addFirstFragment(
                        DashboardFragment.newInstanceDevice(
                            savedInstanceState[DEVICE] as BotBluetoothDevice
                        ),
                        ScanFragment.TAG
                    )
                } else Unit
            }
            Fragments.SETTINGS -> addFirstFragment(ScanFragment.newInstance(), ScanFragment.TAG)
        }

    private fun addFirstFragment(fragment: Fragment, newFragmentTag: String) {
        with(supportFragmentManager) {
            popBackStack()
            beginTransaction()
                .replace(R.id.main_fragment_container, fragment, newFragmentTag)
                .commitAllowingStateLoss()
        }
    }

    override fun addFragment(fragment: Fragment, oldTag: String, newFragmentTag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, fragment, newFragmentTag)
            .addToBackStack(oldTag)
            .commitAllowingStateLoss()
    }

    override fun replaceFragment(fragment: Fragment, oldTag: String, newFragmentTag: String) {
        if (supportFragmentManager.findFragmentByTag(oldTag) != null) {
            if (supportFragmentManager.backStackEntryCount > EMPTY_BACKSTACK_COUNT)
                supportFragmentManager.popBackStack()

            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, fragment, newFragmentTag)
                .commitAllowingStateLoss()
        }
    }

    companion object {
        private const val DEVICE = "device"
        private const val OPEN_FRAGMENT = "open_fragment"

        private const val EMPTY_BACKSTACK_COUNT = 0

        fun newIntentFromNotification(context: Context, device: BluetoothDevice?): Intent =
            Intent(context, MainActivity::class.java)
                .apply {
                    putExtra(OPEN_FRAGMENT, Fragments.DASHBOARD)
                    putExtra(DEVICE, device)
                }
    }

}