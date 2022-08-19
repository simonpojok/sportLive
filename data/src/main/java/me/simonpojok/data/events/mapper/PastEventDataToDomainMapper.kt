package me.simonpojok.data.events.mapper

import me.simonpojok.data.common.mapper.DataToDomainMapper
import me.simonpojok.data.events.model.PastEventDataModel
import me.simonpojok.domain.events.model.EventDomainModel

class PastEventDataToDomainMapper :
    DataToDomainMapper<PastEventDataModel, EventDomainModel.PastEvent>() {
    override fun map(input: PastEventDataModel) = EventDomainModel.PastEvent(
        id = input.id,
        title = input.title,
        subtitle = input.subtitle,
        imageUrl = input.imageUrl,
        videoUrl = input.videoUrl,
        date = input.date
    )
}
