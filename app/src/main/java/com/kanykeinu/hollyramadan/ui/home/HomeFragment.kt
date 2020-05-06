package com.kanykeinu.hollyramadan.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.kanykeinu.hollyramadan.R
import com.kanykeinu.hollyramadan.data.util.DateFormats
import com.kanykeinu.hollyramadan.data.util.asString
import com.kanykeinu.hollyramadan.data.util.today
import com.kanykeinu.hollyramadan.domain.fasting_dates.model.FastingDate
import com.kanykeinu.hollyramadan.ui.home.adapter.DatesAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import ru.nobird.android.ui.adapters.DefaultDelegateAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val calendarAdapter: DefaultDelegateAdapter<FastingDate> = DefaultDelegateAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        homeViewModel.currentDate.observe(viewLifecycleOwner, Observer {
            tv_date.text = it
        })
        homeViewModel.currentDay.observe(viewLifecycleOwner, Observer {
            tv_day.text = it
        })
        homeViewModel.getTodayNotifications().observe(viewLifecycleOwner, Observer {
            tv_sunrise_time_2.text = it.timeStart
            tv_sunset_time_2.text = it.timeFinish
        })
        homeViewModel.getTodayFastingDates().observe(viewLifecycleOwner, Observer {
            tv_sunrise_time.text = it.timeStart
            tv_sunset_time.text = it.timeFinish
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    private fun initList(){
        calendarAdapter += DatesAdapter(today().asString(DateFormats.DATE_MONTH))
        rv_calendar.addItemDecoration(DividerItemDecoration(context, 1))
        rv_calendar.adapter = calendarAdapter
        val fastingDate = FastingDate("0","Date","Day","Suhur", "Iftar")
        homeViewModel.getAllFastingDates().observe(viewLifecycleOwner, Observer {
            val dates = (it as MutableList)
            dates.add(0,fastingDate)
            calendarAdapter.items = dates

        })
    }
}
