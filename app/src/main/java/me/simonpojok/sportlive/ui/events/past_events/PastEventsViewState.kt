package me.simonpojok.sportlive.ui.events.past_events

import me.simonpojok.sportlive.ui.common.viewmodel.ViewState
import me.simonpojok.sportlive.ui.model.EventUiModel.PastEvent

data class PastEventsViewState(
    val events: List<PastEvent> = emptyList()
) : ViewState
