package me.simonpojok.data.repository

import me.simonpojok.domain.events.model.EventDomainModel
import me.simonpojok.domain.events.repository.EventRepository

class EventRepositoryImpl: EventRepository {
    override suspend fun getUpcomingEvents(): List<EventDomainModel.UpcomingEvent> {
        TODO("Not yet implemented")
    }

    override suspend fun getPastEvents(): List<EventDomainModel.PastEvent> {
        TODO("Not yet implemented")
    }
}
