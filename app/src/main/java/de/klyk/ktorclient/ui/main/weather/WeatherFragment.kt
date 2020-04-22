package de.klyk.ktorclient.ui.main.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.klyk.ktorclient.databinding.WeatherFragmentBinding
import org.koin.android.ext.android.inject

class WeatherFragment : Fragment() {

    private val viewModel: WeatherFragmentViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return WeatherFragmentBinding.inflate(inflater).apply {
            lifecycleOwner = this@WeatherFragment
            viewModel = this@WeatherFragment.viewModel
        }.root
    }

    companion object{
        fun newInstance() = WeatherFragment()
    }
}
