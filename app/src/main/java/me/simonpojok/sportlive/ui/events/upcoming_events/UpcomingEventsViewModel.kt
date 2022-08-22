package me.simonpojok.sportlive.ui.events.upcoming_events

import dagger.hilt.android.lifecycle.HiltViewModel
import me.simonpojok.domain.common.usecaseexecutor.UseCaseExecutorProvider
import me.simonpojok.domain.events.model.EventDomainModel
import me.simonpojok.domain.events.usecase.GetUpcomingEventsUseCase
import me.simonpojok.sportlive.ui.common.viewmodel.BaseViewModel
import me.simonpojok.sportlive.ui.common.viewmodel.DialogCommand
import me.simonpojok.sportlive.ui.common.viewmodel.mapper.GeneralDomainToUiExceptionMapper
import me.simonpojok.sportlive.ui.events.mapper.UpcomingEventDomainToUiMapper
import javax.inject.Inject

@HiltViewModel
class UpcomingEventsViewModel @Inject constructor(
    private val getUpcomingEventsUseCase: GetUpcomingEventsUseCase,
    private val upcomingEventDomainToUiMapper: UpcomingEventDomainToUiMapper,
    generalDomainToPresentationExceptionMapper: GeneralDomainToUiExceptionMapper,
    useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<UpcomingEventsViewState, DialogCommand>(
    useCaseExecutorProvider = useCaseExecutorProvider,
    exceptionDomainToUiMapper = generalDomainToPresentationExceptionMapper
) {
    override fun initialState() = UpcomingEventsViewState()

    override fun onFragmentViewCreated() {
        super.onFragmentViewCreated()
        getPastEvents()
    }

    private fun getPastEvents() {
        useCaseExecutor.execute(
            useCase = getUpcomingEventsUseCase,
            callback = ::updatePastEvents,
            onError = { error ->
                print(error.toString())
            }
        )
    }

    private fun updatePastEvents(events: List<EventDomainModel.UpcomingEvent>) {
        updateState { lastState ->
            lastState.copy(
                events = events.map(upcomingEventDomainToUiMapper::toUi)
            )
        }
    }
}
