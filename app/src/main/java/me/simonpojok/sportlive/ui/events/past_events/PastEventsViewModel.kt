package me.simonpojok.sportlive.ui.events.past_events

import dagger.hilt.android.lifecycle.HiltViewModel
import me.simonpojok.domain.common.usecaseexecutor.UseCaseExecutorProvider
import me.simonpojok.domain.events.model.EventDomainModel
import me.simonpojok.domain.events.usecase.GetPastEventsUseCase
import me.simonpojok.sportlive.ui.common.viewmodel.BaseViewModel
import me.simonpojok.sportlive.ui.common.viewmodel.DialogCommand
import me.simonpojok.sportlive.ui.common.viewmodel.mapper.GeneralDomainToUiExceptionMapper
import me.simonpojok.sportlive.ui.events.mapper.PastEventDomainToUiMapper
import javax.inject.Inject

@HiltViewModel
class PastEventsViewModel @Inject constructor(
    private val pastEventDomainToUiMapper: PastEventDomainToUiMapper,
    private val getPastEventsUseCase: GetPastEventsUseCase,
    generalDomainToPresentationExceptionMapper: GeneralDomainToUiExceptionMapper,
    useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<PastEventsViewState, DialogCommand>(
    useCaseExecutorProvider = useCaseExecutorProvider,
    exceptionDomainToUiMapper = generalDomainToPresentationExceptionMapper
) {
    override fun initialState() = PastEventsViewState()

    override fun onFragmentViewCreated() {
        super.onFragmentViewCreated()
        getPastEvents()
    }

    private fun getPastEvents() {
        useCaseExecutor.execute(
            useCase = getPastEventsUseCase,
            callback = ::updatePastEvents,
            onError = ::notifyError
        )
    }

    private fun updatePastEvents(events: List<EventDomainModel.PastEvent>) {
        updateState { lastState ->
            lastState.copy(
                events = events.map(pastEventDomainToUiMapper::toUi)
            )
        }
    }
}
