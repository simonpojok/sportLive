package me.simonpojok.sportlive.ui.events.mapper

import me.simonpojok.domain.events.model.EventDomainModel.UpcomingEvent
import me.simonpojok.sportlive.ui.common.TimestampMapper
import me.simonpojok.sportlive.ui.common.viewmodel.mapper.DomainToUiMapper
import me.simonpojok.sportlive.ui.events.model.EventUiModel

class UpcomingEventDomainToUiMapper(
    private val dateTimeUiMapper: DateTimeUiMapper,
    private val timestampMapper: TimestampMapper
) :
    DomainToUiMapper<UpcomingEvent, EventUiModel.UpcomingEvent>() {
    override fun map(input: UpcomingEvent) = EventUiModel.UpcomingEvent(
        id = input.id,
        title = input.title,
        subtitle = input.subtitle,
        imageUrl = input.imageUrl,
        date = dateTimeUiMapper.toUi(input.date),
        dateString = timestampMapper.toUi(dateTimeUiMapper.toUi(input.date).toLocalDateTime())
    )
}
