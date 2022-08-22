package me.simonpojok.sportlive.ui.video

import dagger.hilt.android.lifecycle.HiltViewModel
import me.simonpojok.domain.common.usecaseexecutor.UseCaseExecutorProvider
import me.simonpojok.sportlive.ui.common.viewmodel.BaseViewModel
import me.simonpojok.sportlive.ui.common.viewmodel.DialogCommand
import me.simonpojok.sportlive.ui.common.viewmodel.mapper.GeneralDomainToUiExceptionMapper
import javax.inject.Inject

@HiltViewModel
class VideoPlayBackViewModel @Inject constructor(
    generalDomainToPresentationExceptionMapper: GeneralDomainToUiExceptionMapper,
    useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<VideoPlayBackViewState, DialogCommand>(
    useCaseExecutorProvider = useCaseExecutorProvider,
    exceptionDomainToUiMapper = generalDomainToPresentationExceptionMapper
) {
    override fun initialState() = VideoPlayBackViewState()

    fun handleNewVideUrl(url: String) {
        updateState { lastState ->
            lastState.copy(
                videoUrl = url
            )
        }
    }
}
