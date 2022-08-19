package me.simonpojok.domain.events.usecase

import kotlinx.coroutines.CoroutineScope
import me.simonpojok.domain.common.usecase.BackgroundExecuteUseCase
import me.simonpojok.domain.common.usecase.BaseUseCase
import me.simonpojok.domain.common.usecase.CoroutineContextProvider
import me.simonpojok.domain.events.model.EventDomainModel.UpcomingEvent
import me.simonpojok.domain.events.repository.EventRepository

interface GetUpcomingEventsUseCase : BaseUseCase<Unit, List<UpcomingEvent>>

class GetUpcomingEventsUseCaseImpl(
    private val eventRepository: EventRepository,
    coroutineContextProvider: CoroutineContextProvider
) : GetUpcomingEventsUseCase, BackgroundExecuteUseCase<Unit, List<UpcomingEvent>>(
    coroutineContextProvider
) {
    override suspend fun executeInBackground(
        request: Unit,
        coroutineScope: CoroutineScope
    ) = eventRepository.getUpcomingEvents()
}
