package com.kanykeinu.hollyramadan.ui.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.kanykeinu.hollyramadan.R
import com.kanykeinu.hollyramadan.domain.fasting_dates.model.FastingDate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_gridlayout.view.*
import ru.nobird.android.ui.adapterdelegates.AdapterDelegate
import ru.nobird.android.ui.adapterdelegates.DelegateViewHolder

class DatesAdapter(private val currentDate: String) :
    AdapterDelegate<FastingDate, DelegateViewHolder<FastingDate>>() {

    override fun isForViewType(position: Int, data: FastingDate): Boolean =
        true

    override fun onCreateViewHolder(parent: ViewGroup): DelegateViewHolder<FastingDate> =
        ViewHolder(createView(parent, R.layout.item_gridlayout))

    inner class ViewHolder(override val containerView: View) :
        DelegateViewHolder<FastingDate>(containerView), LayoutContainer {

        override fun onBind(data: FastingDate) {
            with(data) {
                containerView.tv_weekday.text = this.weekDay
                containerView.tv_date.text = date
                containerView.tv_suhur_time.text = timeStart
                containerView.tv_iftar_time.text = timeFinish
            }
            if (data.date == currentDate) {
                with(context.resources.getColor(R.color.green)) {
                    containerView.tv_weekday.setTextColor(this)
                    containerView.tv_date.setTextColor(this)
                    containerView.tv_suhur_time.setTextColor(this)
                    containerView.tv_iftar_time.setTextColor(this)
                }
            }

        }
    }
}