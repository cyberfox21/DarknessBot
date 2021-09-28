package www.androiddarknessbot.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import www.androiddarknessbot.R
import www.androiddarknessbot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DeviceListAdapter.OnBtnConnectClickListener {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
       ViewModelProvider(this).get(ScanViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissions()
        setupRecyclerView()
        observeViewModel()
    }

    private fun requestPermissions() {
        TODO("Not yet implemented")
    }

    private fun setupRecyclerView(){

    }

    private fun observeViewModel() {
        viewModel.deviceListLD.observe(this, {
            // adapter.submitList = it
        })
    }

    override fun onBtnClick() {
        TODO("Not yet implemented")
    }
}