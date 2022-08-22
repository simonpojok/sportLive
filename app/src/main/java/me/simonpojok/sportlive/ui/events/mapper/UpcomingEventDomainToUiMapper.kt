package me.simonpojok.sportlive.ui.events.mapper

import me.simonpojok.domain.events.model.EventDomainModel.UpcomingEvent
import me.simonpojok.sportlive.ui.common.viewmodel.mapper.DomainToUiMapper
import me.simonpojok.sportlive.ui.events.model.EventUiModel

class UpcomingEventDomainToUiMapper :
    DomainToUiMapper<UpcomingEvent, EventUiModel.UpcomingEvent>() {
    override fun map(input: UpcomingEvent) = EventUiModel.UpcomingEvent(
        id = input.id,
        title = input.title,
        subtitle = input.subtitle,
        imageUrl = input.imageUrl,
        date = input.date
    )
}
