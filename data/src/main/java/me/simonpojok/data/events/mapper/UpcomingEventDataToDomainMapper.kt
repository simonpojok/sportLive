package me.simonpojok.data.events.mapper

import me.simonpojok.data.common.mapper.DataToDomainMapper
import me.simonpojok.data.events.model.UpcomingEventDataModel
import me.simonpojok.domain.events.model.EventDomainModel

class UpcomingEventDataToDomainMapper :
    DataToDomainMapper<UpcomingEventDataModel, EventDomainModel.UpcomingEvent>() {
    override fun map(input: UpcomingEventDataModel) = EventDomainModel.UpcomingEvent(
        id = input.id,
        title = input.title,
        subtitle = input.subtitle,
        imageUrl = input.imageUrl,
        date = input.date
    )
}
