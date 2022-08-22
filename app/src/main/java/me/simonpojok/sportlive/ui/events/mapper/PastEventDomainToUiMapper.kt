package me.simonpojok.sportlive.ui.events.mapper

import me.simonpojok.domain.events.model.EventDomainModel.PastEvent
import me.simonpojok.sportlive.ui.common.TimestampMapper
import me.simonpojok.sportlive.ui.common.viewmodel.mapper.DomainToUiMapper
import me.simonpojok.sportlive.ui.events.model.EventUiModel

class PastEventDomainToUiMapper(
    private val dateTimeUiMapper: DateTimeUiMapper,
    private val timestampMapper: TimestampMapper
) : DomainToUiMapper<PastEvent, EventUiModel.PastEvent>() {
    override fun map(input: PastEvent) = EventUiModel.PastEvent(
        id = input.id,
        title = input.title,
        subtitle = input.subtitle,
        imageUrl = input.imageUrl,
        videoUrl = input.videoUrl,
        date = dateTimeUiMapper.toUi(input.date),
        dateString = timestampMapper.toUi(dateTimeUiMapper.toUi(input.date).toLocalDateTime())
    )
}
