package me.simonpojok.sportlive.ui.events.model

import java.time.ZonedDateTime

sealed class EventUiModel(
    open val id: String,
    open val title: String,
    open val subtitle: String,
    open val date: ZonedDateTime,
    open val imageUrl: String,
    open val dateString: String,
) {
    data class PastEvent(
        override val id: String,
        override val title: String,
        override val subtitle: String,
        override val date: ZonedDateTime,
        override val imageUrl: String,
        val videoUrl: String,
        override val dateString: String,
    ) : EventUiModel(id, title, subtitle, date, imageUrl, dateString)

    data class UpcomingEvent(
        override val id: String,
        override val title: String,
        override val subtitle: String,
        override val date: ZonedDateTime,
        override val imageUrl: String,
        override val dateString: String,
    ) : EventUiModel(id, title, subtitle, date, imageUrl, dateString)
}
