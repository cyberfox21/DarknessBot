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
import www.androiddarknessbot.R
import www.androiddarknessbot.dashboard.presentation.recycler.ScreenMeasurer
import www.androiddarknessbot.dashboard.presentation.recycler.adapter.ImageDelegateAdapter
import www.androiddarknessbot.dashboard.presentation.recycler.adapter.ProgressDelegateAdapter
import www.androiddarknessbot.dashboard.presentation.recycler.adapter.TextDelegateAdapter
import www.androiddarknessbot.dashboard.presentation.recycler.delegate.MainAdapter
import www.androiddarknessbot.databinding.FragmentDashboardBinding
import www.androiddarknessbot.scan.domain.entity.BotBluetoothDevice

class DashboardFragment : Fragment() {

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
    }

    private fun processDemoMode() {
        viewModel.callDemoAdapter()
    }

    private fun setupRecyclerView() {
        val screenMeasurer = ScreenMeasurer(requireActivity())
        rvAdapter = MainAdapter()
        rvAdapter.addDelegate(ProgressDelegateAdapter(screenMeasurer))
        rvAdapter.addDelegate(ImageDelegateAdapter(screenMeasurer))
        rvAdapter.addDelegate(TextDelegateAdapter(screenMeasurer))

        viewModel.characteristicList.observe(viewLifecycleOwner) { rvAdapter.submitList(it) }

        binding.rvDashboard.adapter = rvAdapter
        binding.rvDashboard.layoutManager = GridLayoutManager(
            activity?.applicationContext,
            ScreenMeasurer.OBJECTS_IN_COL,
            HORIZONTAL,
            false
        )
    }

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