package www.androiddarknessbot.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import www.androiddarknessbot.ScanService
import www.androiddarknessbot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DeviceListAdapter.OnBtnConnectClickListener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(ScanViewModel::class.java)
    }

    private val adapter by lazy {
        DeviceListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupScanButton()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        with(binding) {
            searchRecyclerView.adapter = adapter
        }
    }

    private fun observeViewModel() {
        // viewModel.startScan()
        //viewModel.scanState.observe
        viewModel.deviceListLD.observe(this, {
            adapter.submitList(it)
        })
        startService(ScanService.newIntent(this))
    }

    private fun setupScanButton() {
        with(binding) {
            ibtnStartScan.setOnClickListener {
                //viewModel.startScan()

                if (lvAnimation.isAnimating) {
                    lvAnimation.cancelAnimation()
                    lvAnimation.isVisible = false
                } else {
                    lvAnimation.playAnimation()
                    lvAnimation.isVisible = true
                }
            }
        }
    }

    fun showDeviceDataScreen() {
        //DeviceDataActivity.createIntent(this, )
    }

    override fun onBtnConnectClick() {
        showDeviceDataScreen()
    }
}