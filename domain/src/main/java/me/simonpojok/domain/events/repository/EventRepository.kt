package me.simonpojok.domain.events.repository

import me.simonpojok.domain.events.model.EventDomainModel.PastEvent
import me.simonpojok.domain.events.model.EventDomainModel.UpcomingEvent

interface EventRepository {
    suspend fun getUpcomingEvents(): List<UpcomingEvent>
    suspend fun getPastEvents(): List<PastEvent>
}
