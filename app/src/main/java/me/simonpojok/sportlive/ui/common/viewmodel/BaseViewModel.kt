package me.simonpojok.sportlive.ui.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import me.simonpojok.domain.common.model.DomainException
import me.simonpojok.domain.common.usecase.UseCaseExecutor
import me.simonpojok.domain.common.usecaseexecutor.UseCaseExecutorProvider
import me.simonpojok.sportlive.ui.common.viewmodel.exception.UiException
import me.simonpojok.sportlive.ui.common.viewmodel.exception.SilentUiException
import me.simonpojok.sportlive.ui.common.viewmodel.mapper.DomainToUiMapper
import me.simonpojok.sportlive.ui.common.viewmodel.mapper.GeneralDomainToUiExceptionMapper

abstract class BaseViewModel<VIEW_STATE : ViewState, DIALOG_COMMAND : DialogCommand>(
    useCaseExecutorProvider: UseCaseExecutorProvider,
    private val exceptionDomainToUiMapper: DomainToUiMapper<in DomainException, out UiException>
) : ViewModel() {
    open val generalExceptionMapper:
        DomainToUiMapper<in DomainException, out UiException> =
        GeneralDomainToUiExceptionMapper()

    val navigationCommands = SingleLiveEvent<Destination>()
    val snackBarEvents = SingleLiveEvent<Int>()
    val snackBarStringEvents = SingleLiveEvent<String>()

    val notificationState = SingleLiveEvent<NotificationState>()
    val presentationExceptionEvents = SingleLiveEvent<UiException>()
    private val _viewState = MutableLiveData<VIEW_STATE>().apply { value = initialState() }
    val viewState: LiveData<VIEW_STATE>
        get() = _viewState

    val dialogEvents = SingleLiveEvent<DIALOG_COMMAND>()
    val dialogLoadingState = MutableLiveData(false)
    val useCaseExecutor: UseCaseExecutor = useCaseExecutorProvider(viewModelScope)
    val globalUseCaseExecutor: UseCaseExecutor = useCaseExecutorProvider(GlobalScope)

    fun notify(dialogCommand: DIALOG_COMMAND) {
        dialogEvents.value = dialogCommand
    }

    protected fun notifyLoading(message: String) {
        notificationState.value = NotificationState.Loading(message)
    }

    protected fun notifyFailure(message: String, exception: UiException) {
        notificationState.value = NotificationState.Failure(message)
    }

    protected fun notifySuccess(message: String) {
        notificationState.value = NotificationState.Success(message)
    }

    protected fun notifyNormal(message: String) {
        notificationState.value = NotificationState.Normal(message)
    }

    fun currentViewState() = viewState.value ?: initialState()

    open fun onFragmentCreate() = Unit
    open fun onFragmentViewCreated() = Unit
    open fun onFragmentResume() = Unit
    open fun onFragmentDestroyView() = Unit
    open fun onFragmentPause() = Unit
    open fun onFragmentStop() = Unit
    open fun onFragmentStart() = Unit

    abstract fun initialState(): VIEW_STATE

    protected fun navigate(uiDestination: Destination) {
        navigationCommands.value = uiDestination
    }

    fun updateState(newViewState: VIEW_STATE) {
        _viewState.value = newViewState
    }

    fun updateState(updatedState: (lastState: VIEW_STATE) -> VIEW_STATE) =
        updateState(updatedState(currentViewState()))

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel("Scope cleared")
    }

    protected fun setLoading(state: Boolean) {
        dialogLoadingState.value = state
    }

    fun notifyError(exception: DomainException) {
        notifyError(generalExceptionMapper.toUi(exception))
    }

    fun notifyError(exception: UiException) {
        if (exception !is SilentUiException) {
            presentationExceptionEvents.value = exception
        }
    }
}

sealed class NotificationState(open val message: String) {
    data class Success(override val message: String) : NotificationState(message)
    data class Loading(override val message: String) : NotificationState(message)
    data class Failure(override val message: String) : NotificationState(message)
    data class Normal(override val message: String) : NotificationState(message)
}
