package me.simonpojok.data.events.mapper

import me.simonpojok.data.common.mapper.DataToDomainMapper
import me.simonpojok.data.events.model.EventDataModel.UpcomingEvent
import me.simonpojok.domain.events.model.EventDomainModel

class UpcomingEventDataToDomainMapper :
    DataToDomainMapper<UpcomingEvent, EventDomainModel.UpcomingEvent>() {
    override fun map(input: UpcomingEvent) = EventDomainModel.UpcomingEvent(
        id = input.id,
        title = input.title,
        subtitle = input.subtitle,
        imageUrl = input.imageUrl,
        date = input.date
    )
}
