package me.simonpojok.sportlive.ui.video

import me.simonpojok.sportlive.ui.common.viewmodel.LoadingState
import me.simonpojok.sportlive.ui.common.viewmodel.ViewState

data class VideoPlayBackViewState(
    val videoUrl: String = "",
    override val loadingState: LoadingState = LoadingState.Idle
) : ViewState(loadingState)
