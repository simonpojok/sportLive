package me.simonpojok.sportlive.ui.common.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import me.simonpojok.sportlive.ui.common.fragments.destination.UiDestinationToNavigationMapper
import me.simonpojok.sportlive.ui.common.viewmodel.BaseViewModel
import me.simonpojok.sportlive.ui.common.viewmodel.Destination
import me.simonpojok.sportlive.ui.common.viewmodel.DialogCommand
import me.simonpojok.sportlive.ui.common.viewmodel.ViewState

abstract class BaseFragment<VIEW_STATE : ViewState, DIALOG_COMMAND : DialogCommand> :
    Fragment() {
    abstract val layout: Int
    abstract val destinationToNavigationMapper: UiDestinationToNavigationMapper
    abstract val viewModel: BaseViewModel<VIEW_STATE, DIALOG_COMMAND>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    open fun renderViewState(viewState: VIEW_STATE) = Unit

    open fun renderDialog(dialogCommand: DIALOG_COMMAND) = Unit

    open fun onFragmentResumed() {
        viewModel.onFragmentResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onFragmentCreate()
    }

    @CallSuper
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelInternal()
        viewModel.onFragmentViewCreated()
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        viewModel.onFragmentPause()
    }

    private fun observeViewModelInternal() {
        viewModel.navigationCommands.observe(
            viewLifecycleOwner,
            NavigationObserver(destinationToNavigationMapper, findNavController())
        )
        viewModel.viewState.observe(viewLifecycleOwner, RenderStateObserver())
        viewModel.dialogEvents.observe(viewLifecycleOwner, DialogEventsObserver())
    }

    override fun onStart() {
        viewModel.onFragmentStart()
        super.onStart()
    }

    override fun onStop() {
        viewModel.onFragmentStop()
        super.onStop()
    }

    override fun onDestroyView() {
        viewModel.onFragmentDestroyView()
        super.onDestroyView()
    }

    private class NavigationObserver(
        private val destinationToNavigationMapper: UiDestinationToNavigationMapper,
        private val navController: NavController
    ) : Observer<Destination> {
        override fun onChanged(uiDestination: Destination) {
            try {
                destinationToNavigationMapper.map(uiDestination).navigate(navController)
            } catch (exception: IllegalArgumentException) {
            }
        }
    }

    inner class RenderStateObserver : Observer<VIEW_STATE> {
        override fun onChanged(viewState: VIEW_STATE) = renderViewState(viewState)
    }

    inner class DialogEventsObserver : Observer<DIALOG_COMMAND> {
        override fun onChanged(dialogCommand: DIALOG_COMMAND) = renderDialog(dialogCommand)
    }
}
