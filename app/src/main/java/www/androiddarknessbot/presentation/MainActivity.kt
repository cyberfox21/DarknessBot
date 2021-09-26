package www.androiddarknessbot.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import www.androiddarknessbot.R

class MainActivity : AppCompatActivity(), DeviceListAdapter.OnBtnConnectClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // requestPermissions
        // setupRecyclerView
        // observe viewModel
    }

    override fun onBtnClick() {
        TODO("Not yet implemented")
    }
}