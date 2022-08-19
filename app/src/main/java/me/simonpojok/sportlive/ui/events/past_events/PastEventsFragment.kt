package me.simonpojok.sportlive.ui.events.past_events

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import me.simonpojok.sportlive.R
import me.simonpojok.sportlive.ui.common.fragments.BaseFragment
import me.simonpojok.sportlive.ui.common.viewmodel.DialogCommand
import javax.inject.Inject

@AndroidEntryPoint
class PastEventsFragment : BaseFragment<PastEventsViewState, DialogCommand>() {
    override val layout = R.layout.fragment_home

    @Inject
    override lateinit var destinationToNavigationMapper: PastEventsFragmentDestinationMapper

    override val viewModel: PastEventsViewModel by viewModels()

    override fun renderViewState(viewState: PastEventsViewState) {
        super.renderViewState(viewState)
    }
}
