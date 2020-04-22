package de.klyk.ktorclient.ui.main.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import de.klyk.ktorclient.databinding.MainTabsFragmentBinding
import de.klyk.ktorclient.ui.base.viewpager2.FragmentStateViewPagerAdapter
import de.klyk.ktorclient.ui.main.DummyFragment
import de.klyk.ktorclient.ui.main.lokalserver.LokalServerFragment
import de.klyk.ktorclient.ui.main.weather.WeatherFragment
import de.klyk.ktorclient.ui.main.websocket.WebsocketsFragment
import kotlinx.android.synthetic.main.main_tabs_fragment.*
import timber.log.Timber

class MainTabsFragment : Fragment() {

    private lateinit var tabAdapter: FragmentStateViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MainTabsFragmentBinding.inflate(inflater).apply {
            lifecycleOwner = this@MainTabsFragment
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabAdapter = FragmentStateViewPagerAdapter(childFragmentManager, lifecycle)
        addFragmentsToViewPager()
        initTabLayout()
    }

    private fun addFragmentsToViewPager() {
        tabAdapter.addFragment(WeatherFragment.newInstance(), "HTTP - Wetter")
        tabAdapter.addFragment(LokalServerFragment.newInstance(), "HTTP - Lokalserver")
        tabAdapter.addFragment(DummyFragment(), "HTTP - Mock")
        tabAdapter.addFragment(WebsocketsFragment.newInstance(), "Websocket")

        //ViewPager Adapter erhält TabAdapter mit den Fragmenten, die innerhalb des TabAdapter hinzugefügt worden sind
        viewPagerForTabs.adapter = tabAdapter
    }

    private fun initTabLayout() {
        TabLayoutMediator(mainTabs_TL, viewPagerForTabs){ tab, position ->
            //Alle Tab-Title werden gesetzt
            tab.text = tabAdapter.getPageTitle(position)
            Timber.d("Tabs $position")
        }.attach()
        viewPagerForTabs.currentItem = 0
    }
}
