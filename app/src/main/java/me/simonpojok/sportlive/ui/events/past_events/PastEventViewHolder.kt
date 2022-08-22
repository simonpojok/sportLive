package me.simonpojok.sportlive.ui.events.past_events

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import me.simonpojok.sportlive.R
import me.simonpojok.sportlive.ui.common.fragments.ItemsListAdapter.ViewHolder
import me.simonpojok.sportlive.ui.events.model.EventUiModel

class PastEventViewHolder(
    itemView: View
) : ViewHolder<EventUiModel>(itemView) {
    private val titleView: TextView by lazy { itemView.findViewById(R.id.event_title) }
    private val subTitleView: TextView by lazy { itemView.findViewById(R.id.event_subtitle) }
    private val dateView: TextView by lazy { itemView.findViewById(R.id.event_date) }
    private val eventImageView: ImageView by lazy { itemView.findViewById(R.id.event_image) }

    override fun bind(item: EventUiModel) {
        titleView.text = item.title
        subTitleView.text = item.subtitle
        dateView.text = item.date

        Picasso.get().load(item.imageUrl)
            .fit()
            .centerCrop()
            .into(eventImageView)
    }
}
