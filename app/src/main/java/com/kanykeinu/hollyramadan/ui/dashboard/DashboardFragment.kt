package com.kanykeinu.hollyramadan.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.kanykeinu.hollyramadan.R
import com.kanykeinu.hollyramadan.domain.fasting_dates.model.FastingDate
import com.kanykeinu.hollyramadan.domain.hadith.model.Hadith
import com.kanykeinu.hollyramadan.ui.dashboard.adapter.HadithsAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.*
import ru.nobird.android.ui.adapters.DefaultDelegateAdapter

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private val hadithAdapter: DefaultDelegateAdapter<Hadith> = DefaultDelegateAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    private fun initList() {
        hadithAdapter += HadithsAdapter()
        view_pager.adapter = hadithAdapter
        view_pager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tv_current_hadith.text = "${position+1}/ ${hadithAdapter.items.count()}"
            }
        })
        dashboardViewModel.getHadiths().observe(viewLifecycleOwner, Observer {
            hadithAdapter.items = it
            tv_current_hadith.text = "1/${hadithAdapter.items.count()}"
        })
    }


}
