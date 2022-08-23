package me.simonpojok.sportlive.ui.events.upcoming_events

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
    private var eventsRefreshJob: Job? = null
    override fun initialState() = UpcomingEventsViewState()

    override fun onFragmentViewCreated() {
        super.onFragmentViewCreated()
        onRefreshEvents()
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
                    .sortedBy { it.date }
            )
        }
    }

    private fun onRefreshEvents() {
        onCancelEventsRefresh()
        eventsRefreshJob = viewModelScope.launch {
            while(true) {
                getPastEvents()
                delay(30000)
            }
        }
    }

    private fun onCancelEventsRefresh() {
        eventsRefreshJob?.cancel()
        eventsRefreshJob = null
    }

    override fun onFragmentDestroyView() {
        onCancelEventsRefresh()
        super.onFragmentDestroyView()
    }
}
