package www.androiddarknessbot.scan.presentation

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionInflater
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.*
import www.androiddarknessbot.core.domain.BotBluetoothDevice
import www.androiddarknessbot.scan.R
import www.androiddarknessbot.scan.databinding.FragmentScanBinding
import www.androiddarknessbot.scan.presentation.recycler.DeviceListAdapter
import javax.inject.Inject

/**
 * @author t.shkolnik
 */
class ScanFragment @Inject constructor(
    private val scanRouter: ScanRouter
) : Fragment() {

    private var _binding: FragmentScanBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException(getString(R.string.null_fragment_scan_binding))

    private lateinit var viewModel: ScanViewModel

    private lateinit var transitionInflater: TransitionInflater

//    private val connectClickListener = object : ScanRouter {
//        override fun onBtnConnectClick(device: BotBluetoothDevice) =
//            showDashboardInDeviceMode(device)
//    }

    private val adapter by lazy { DeviceListAdapter(scanRouter) }

    @RequiresApi(Build.VERSION_CODES.N)
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_BACKGROUND_LOCATION, false) -> {
                log("BACKGROUND_LOCATION permission granted")
                checkLocation()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                log("FINE_LOCATION permission granted")
                checkLocation()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                log("COARSE_LOCATION permission granted")
                checkLocation()
            }
            else -> log("No location access granted")
        }
    }

    private var requestLocation = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) enableBluetooth()
        else {
            log("CANCEL")
            log("Please Accept Location enable for use this App.")
        }
    }

    private var requestBluetooth = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            log("bluetooth enabled")
            viewModel.startScan()
        } else { /* denied */
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScanBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        transitionInflater = TransitionInflater.from(requireContext())
        returnTransition = transitionInflater.inflateTransition(R.transition.slide_right)
        setupToolbar()
        setupScanButton()
        setupRecyclerView()
        setupViewModel()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupToolbar() {
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            shutDown()
            when (menuItem.itemId) {
                R.id.toDemo -> scanRouter.showDashboardInDemoMode(oldFragmentTag = TAG)
                R.id.toSettings -> scanRouter.showSettings(oldFragmentTag = TAG)
            }
            true
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setupScanButton() {
        binding.ibtnStartScan.setOnClickListener {
            when (viewModel.state.value) {
                ScanScreenState.Empty, is ScanScreenState.Result -> checkAllPermissions()
                is ScanScreenState.Scanning -> viewModel.stopScan()
                null -> Unit
            }
        }
    }

    private fun setupRecyclerView() {
        binding.searchRecyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[ScanViewModel::class.java]
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { scanScreenState ->
            when (scanScreenState) {
                ScanScreenState.Empty -> processEmpty()
                is ScanScreenState.Scanning -> processScanning(scanScreenState.devices)
                is ScanScreenState.Result -> processResult(scanScreenState.devices)
            }
        }
    }

    private fun processEmpty() {
        binding.searchRecyclerView.isVisible = false
        binding.emptyLayout.errorLayout.isVisible = true
        stopAnimation()
    }

    private fun processScanning(content: List<BotBluetoothDevice>) {
        binding.searchRecyclerView.isVisible = true
        binding.emptyLayout.errorLayout.isVisible = false
        if (isAnimationActive().not()) startAnimation()
        adapter.submitList(content)
    }

    private fun processResult(content: List<BotBluetoothDevice>) {
        binding.searchRecyclerView.isVisible = true
        binding.emptyLayout.errorLayout.isVisible = false
        stopAnimation()
        adapter.submitList(content)
    }

    private fun startAnimation() {
        binding.collapsingToolbarLayout.title = getString(R.string.scanning)
        binding.lvAnimation.playAnimation()
        binding.lvAnimation.isVisible = true
    }

    private fun stopAnimation() {
        binding.collapsingToolbarLayout.title = getString(R.string.tap_for_scanning)
        binding.lvAnimation.cancelAnimation()
        binding.lvAnimation.isVisible = false
    }

    private fun isAnimationActive() = binding.lvAnimation.isAnimating

//    private fun showDashboardInDeviceMode(device: BotBluetoothDevice) {
//        shutDown()
//        scanRouter.showDashboardInDeviceMode(device = device, oldFragmentTag = TAG)
//    }
//
//    private fun showDashboardInDemoMode() {
//        shutDown()
//        scanRouter.showDashboardInDemoMode(oldFragmentTag = TAG)
//    }
//
//    private fun showSettings() {
//        shutDown()
//        scanRouter.showSettings(oldFragmentTag = TAG)
//    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun checkAllPermissions() {
        var appPerms =
            arrayOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            appPerms += arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            appPerms += arrayOf(
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_CONNECT
            )
        }
        locationPermissionRequest.launch(appPerms)
    }

    private fun enableBluetooth() =
        requestBluetooth.launch(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE))

    private fun checkLocation() {
        val locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER).not()) enableLocation()
        else enableBluetooth()
    }

    private fun enableLocation() {
        val locationRequest = LocationRequest.create()
        locationRequest.apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = LOCATION_REQUEST_INTERVAL
            fastestInterval = FASTEST_REQUEST_INTERVAL
        }

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .setAlwaysShow(true)

        val result = LocationServices
            .getSettingsClient(requireContext())
            .checkLocationSettings(builder.build())

        result.addOnCompleteListener {
            try {
                val response: LocationSettingsResponse = it.getResult(ApiException::class.java)
                log("${response.locationSettingsStates?.isGpsPresent}")
                if (response.locationSettingsStates?.isGpsPresent == true) log("location enabled")
            } catch (e: ApiException) {
                when (e.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                        val intentSenderRequest =
                            e.status.resolution?.let { it1 ->
                                IntentSenderRequest.Builder(it1).build()
                            }
                        requestLocation.launch(intentSenderRequest)
                    } catch (e: IntentSender.SendIntentException) {
                    }
                }
            }
        }
    }

    private fun log(message: String) {
        Log.d("CHECKER", "ScanFragment: $message")
    }

    private fun shutDown() {
        viewModel.stopScan()
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

    companion object {
        const val TAG = "fragment_scan"
        const val FASTEST_REQUEST_INTERVAL = 5 * 1000L
        const val LOCATION_REQUEST_INTERVAL = 30 * 1000L
    }
}
