package me.simonpojok.data.events.repository

import me.simonpojok.data.events.mapper.PastEventDataToDomainMapper
import me.simonpojok.data.events.mapper.UpcomingEventDataToDomainMapper
import me.simonpojok.data.events.remote.EventsService
import me.simonpojok.domain.events.model.EventDomainModel.UpcomingEvent
import me.simonpojok.domain.events.repository.EventRepository

class EventRepositoryImpl(
    private val upcomingEventDataToDomainMapper: UpcomingEventDataToDomainMapper,
    private val pastEventDataToDomainMapper: PastEventDataToDomainMapper,
    private val eventsService: EventsService
) : EventRepository {
    override suspend fun getUpcomingEvents(): List<UpcomingEvent> {
        try {
            return eventsService.getUpcomingEvents()
                .map(upcomingEventDataToDomainMapper::toDomain)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPastEvents() =
        eventsService.getPastEvents().map(pastEventDataToDomainMapper::toDomain)
}
