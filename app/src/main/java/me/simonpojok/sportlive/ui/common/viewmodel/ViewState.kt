package me.simonpojok.sportlive.ui.common.viewmodel

sealed interface LoadingState {
    object Loading: LoadingState
    object Done: LoadingState
    object Idle: LoadingState
    object Error: LoadingState
}

abstract class ViewState(open val loadingState: LoadingState)
