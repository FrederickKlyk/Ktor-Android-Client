package de.klyk.ktorclient.ui.main.lokalserver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import de.klyk.ktorclient.databinding.LokalServerFragmentBinding
import org.koin.android.ext.android.inject

class LokalServerFragment : Fragment() {

    val viewModel: LokalServerFragmentViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LokalServerFragmentBinding.inflate(inflater).apply {
            lifecycleOwner = this@LokalServerFragment
            viewModel = this@LokalServerFragment.viewModel
        }.root
    }

    companion object{
        fun newInstance() = LokalServerFragment()
    }
}
