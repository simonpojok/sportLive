package me.simonpojok.sportlive.ui.events.model

sealed class EventUiModel(
    open val id: String,
    open val title: String,
    open val subtitle: String,
    open val date: String,
    open val imageUrl: String
) {
    data class PastEvent(
        override val id: String,
        override val title: String,
        override val subtitle: String,
        override val date: String,
        override val imageUrl: String,
        val videoUrl: String
    ) : EventUiModel(id, title, subtitle, date, imageUrl)

    data class UpcomingEvent(
        override val id: String,
        override val title: String,
        override val subtitle: String,
        override val date: String,
        override val imageUrl: String,
    ) : EventUiModel(id, title, subtitle, date, imageUrl)
}
