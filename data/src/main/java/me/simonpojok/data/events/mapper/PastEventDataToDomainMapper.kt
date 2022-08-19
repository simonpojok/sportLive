package me.simonpojok.data.events.mapper

import me.simonpojok.data.common.mapper.DataToDomainMapper
import me.simonpojok.data.events.model.EventDataModel.PastEvent
import me.simonpojok.domain.events.model.EventDomainModel

class PastEventDataToDomainMapper : DataToDomainMapper<PastEvent, EventDomainModel.PastEvent>() {
    override fun map(input: PastEvent) = EventDomainModel.PastEvent(
        id = input.id,
        title = input.title,
        subtitle = input.subtitle,
        imageUrl = input.imageUrl,
        videoUrl = input.videoUrl,
        date = input.date
    )
}
