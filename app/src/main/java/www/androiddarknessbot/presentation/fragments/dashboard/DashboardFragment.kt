package www.androiddarknessbot.presentation.fragments.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import www.androiddarknessbot.R
import www.androiddarknessbot.databinding.FragmentDashboardBinding
import www.androiddarknessbot.domain.entity.BotBluetoothDevice

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException(getString(R.string.null_fragment_dashboard))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {

        private const val DEVICE = "device"
        const val TAG = "fragment_dashboard"

        fun newInstance(device: BotBluetoothDevice): DashboardFragment {
            val args = Bundle().apply { putParcelable(DEVICE, device) }
            return DashboardFragment().apply { arguments = args }
        }
    }

}