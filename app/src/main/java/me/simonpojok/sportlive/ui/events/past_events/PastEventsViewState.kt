package me.simonpojok.sportlive.ui.events.past_events

import me.simonpojok.sportlive.ui.common.viewmodel.LoadingState
import me.simonpojok.sportlive.ui.common.viewmodel.ViewState
import me.simonpojok.sportlive.ui.events.model.EventUiModel.PastEvent

data class PastEventsViewState(
    val events: List<PastEvent> = emptyList(),
    override val loadingState: LoadingState = LoadingState.Idle
) : ViewState(loadingState)
