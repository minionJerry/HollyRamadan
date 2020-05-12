package com.kanykeinu.hollyramadan.ui.dashboard.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.kanykeinu.hollyramadan.R
import com.kanykeinu.hollyramadan.domain.fasting_dates.model.FastingDate
import com.kanykeinu.hollyramadan.domain.hadith.model.Hadith
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_gridlayout.view.*
import kotlinx.android.synthetic.main.item_view_pager.view.*
import ru.nobird.android.ui.adapterdelegates.AdapterDelegate
import ru.nobird.android.ui.adapterdelegates.DelegateViewHolder

class HadithsAdapter :
    AdapterDelegate<Hadith, DelegateViewHolder<Hadith>>() {

    override fun isForViewType(position: Int, data: Hadith): Boolean =
        true

    override fun onCreateViewHolder(parent: ViewGroup): DelegateViewHolder<Hadith> =
        ViewHolder(createView(parent, R.layout.item_view_pager))

    inner class ViewHolder(override val containerView: View) :
        DelegateViewHolder<Hadith>(containerView), LayoutContainer {

        override fun onBind(data: Hadith) {
            with(data) {
                containerView.tv_hadith_text.text = text
                containerView.tv_source.text = source
            }
        }
    }
}