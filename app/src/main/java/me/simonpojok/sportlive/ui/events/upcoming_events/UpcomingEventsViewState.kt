package me.simonpojok.sportlive.ui.events.upcoming_events

import me.simonpojok.sportlive.ui.common.viewmodel.LoadingState
import me.simonpojok.sportlive.ui.common.viewmodel.ViewState
import me.simonpojok.sportlive.ui.events.model.EventUiModel

data class UpcomingEventsViewState(
    val events: List<EventUiModel.UpcomingEvent> = emptyList(),
    override val loadingState: LoadingState = LoadingState.Idle
) : ViewState(loadingState)
