package de.klyk.ktorclient.ui.main.websocket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import de.klyk.ktorclient.databinding.WebsocketsFragmentBinding
import kotlinx.android.synthetic.main.websockets_fragment.*
import org.koin.android.ext.android.inject

class WebsocketsFragment : Fragment() {

    val viewModel: WebsocketsFragmentViewModel by inject()
    val adapter: WebsocketsFragmentAdapter by lazy { WebsocketsFragmentAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return WebsocketsFragmentBinding.inflate(inflater).apply {
            lifecycleOwner = this@WebsocketsFragment
            viewModel = this@WebsocketsFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecylerview()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.wsMessageSend.observe(viewLifecycleOwner, Observer {
            adapter.addMessage(it.first, it.second)
        })
        viewModel.wsResponse.observe(viewLifecycleOwner, Observer {
            adapter.addMessage(it.first, it.second)
        })
    }

    private fun setupRecylerview() {
        websocketsMessagesRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        websocketsMessagesRV.adapter = adapter
    }

    companion object{
        fun newInstance() = WebsocketsFragment()
    }
}
