package www.androiddarknessbot.dashboard.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.transition.TransitionInflater
import www.androiddarknessbot.core.domain.BotBluetoothDevice
import www.androiddarknessbot.dashboard.R
import www.androiddarknessbot.dashboard.data.BluetoothService
import www.androiddarknessbot.dashboard.data.BluetoothService.Companion.ACTION_START_SERVICE
import www.androiddarknessbot.dashboard.data.BluetoothService.Companion.ACTION_STOP_SERVICE
import www.androiddarknessbot.dashboard.databinding.FragmentDashboardBinding
import www.androiddarknessbot.dashboard.presentation.recycler.ScreenMeasurer
import www.androiddarknessbot.dashboard.presentation.recycler.adapter.ImageDelegateAdapter
import www.androiddarknessbot.dashboard.presentation.recycler.adapter.ProgressDelegateAdapter
import www.androiddarknessbot.dashboard.presentation.recycler.adapter.TextDelegateAdapter
import www.androiddarknessbot.dashboard.presentation.recycler.delegate.MainAdapter
import javax.inject.Inject

/**
 * @author t.shkolnik
 */
class DashboardFragment @Inject constructor() : Fragment() {

    private var isServiceWorking = false

    private var _binding: FragmentDashboardBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException(getString(R.string.null_fragment_dashboard))

    private val viewModel by lazy { ViewModelProvider(this)[DashboardViewModel::class.java] }

    private lateinit var rvAdapter: MainAdapter

    private lateinit var transitionInflater: TransitionInflater
    private lateinit var dashboardMode: DashboardScreenMode
    private lateinit var device: BotBluetoothDevice

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parseArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCustomTransitions()
        setupRecyclerView()
        launchRightMode()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        if (dashboardMode == DashboardScreenMode.DEVICE && isServiceWorking) stopBluetoothService()
        super.onDestroy()
    }

    private fun setCustomTransitions() {
        transitionInflater = TransitionInflater.from(requireContext())
        enterTransition = transitionInflater.inflateTransition(R.transition.slide_right)
        exitTransition = transitionInflater.inflateTransition(R.transition.fade)
    }

    private fun parseArguments() {
        if (arguments?.containsKey(MODE) == false) throw RuntimeException("Dashboard mode is absent")
        dashboardMode = getModeFromString(arguments?.get(MODE) as String)
        if (dashboardMode == DashboardScreenMode.DEVICE) {
            if (arguments?.containsKey(DEVICE) == false) throw RuntimeException("Device is absent")
            device = arguments?.getParcelable(DEVICE)!!
        }
    }

    private fun getModeFromString(str: String) =
        DashboardScreenMode.values().find { it.title == str } ?: DashboardScreenMode.DEMO

    private fun launchRightMode() = when (dashboardMode) {
        DashboardScreenMode.DEVICE -> processDeviceMode()
        DashboardScreenMode.DEMO -> processDemoMode()
    }

    private fun processDeviceMode() {
        /* check if it is EUC */
        //viewModel.callWheelResolver()
//        viewModel.shouldCallBluetoothService.observe(this) {
//            when (it) {
//                true -> activity?.startService(intentToBluetoothService)
//                false -> activity?.stopService(intentToBluetoothService)
//            }
//        }
        if (isServiceWorking) stopBluetoothService()
        startBluetoothService()

    }

    private fun processDemoMode() {
        viewModel.characteristicList.observe(viewLifecycleOwner) { rvAdapter.submitList(it) }
        viewModel.callDemoAdapter()
    }

    private fun setupRecyclerView() {
        val screenMeasurer = ScreenMeasurer(requireActivity())
        rvAdapter = MainAdapter()
        rvAdapter.addDelegate(ProgressDelegateAdapter(screenMeasurer))
        rvAdapter.addDelegate(ImageDelegateAdapter(screenMeasurer))
        rvAdapter.addDelegate(TextDelegateAdapter(screenMeasurer))

        binding.rvDashboard.itemAnimator?.addDuration = 0
        binding.rvDashboard.itemAnimator?.changeDuration = 0

        binding.rvDashboard.adapter = rvAdapter
        binding.rvDashboard.layoutManager = GridLayoutManager(
            activity?.applicationContext,
            ScreenMeasurer.OBJECTS_IN_COL,
            HORIZONTAL,
            false
        )
    }

    private fun startBluetoothService() = sendCommandToService(ACTION_START_SERVICE)

    private fun stopBluetoothService() = sendCommandToService(ACTION_STOP_SERVICE)

    private fun sendCommandToService(action: String) =
        activity?.startService(BluetoothService.newIntent(requireContext(), action))

    companion object {

        private const val DEVICE = "device"
        private const val MODE = "mode"
        const val TAG = "fragment_dashboard"

        fun newInstanceDevice(device: BotBluetoothDevice): DashboardFragment =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putString(MODE, DashboardScreenMode.DEVICE.title)
                    putParcelable(DEVICE, device)
                }
            }

        fun newInstanceDemo(): DashboardFragment {
            val args = Bundle().apply { putString(MODE, DashboardScreenMode.DEMO.title) }
            return DashboardFragment().apply { arguments = args }
        }
    }

}
