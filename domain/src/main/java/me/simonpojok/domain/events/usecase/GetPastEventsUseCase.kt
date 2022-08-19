package me.simonpojok.domain.events.usecase

import kotlinx.coroutines.CoroutineScope
import me.simonpojok.domain.common.usecase.BackgroundExecuteUseCase
import me.simonpojok.domain.common.usecase.BaseUseCase
import me.simonpojok.domain.common.usecase.CoroutineContextProvider
import me.simonpojok.domain.events.model.EventDomainModel.PastEvent
import me.simonpojok.domain.events.repository.EventRepository

interface GetPastEventsUseCase : BaseUseCase<Unit, List<PastEvent>>

class GetPastEventsUseCaseImpl(
    private val eventRepository: EventRepository,
    coroutineContextProvider: CoroutineContextProvider
) : GetPastEventsUseCase, BackgroundExecuteUseCase<Unit, List<PastEvent>>(
    coroutineContextProvider
) {
    override suspend fun executeInBackground(
        request: Unit,
        coroutineScope: CoroutineScope
    ) = eventRepository.getPastEvents()
}
