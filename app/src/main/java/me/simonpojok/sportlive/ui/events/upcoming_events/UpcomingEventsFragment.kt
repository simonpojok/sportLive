package me.simonpojok.sportlive.ui.events.upcoming_events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import me.simonpojok.sportlive.R
import me.simonpojok.sportlive.ui.common.fragments.BaseFragment
import me.simonpojok.sportlive.ui.common.fragments.ItemsListAdapter
import me.simonpojok.sportlive.ui.common.viewmodel.DialogCommand
import me.simonpojok.sportlive.ui.events.model.EventUiModel
import me.simonpojok.sportlive.ui.events.past_events.PastEventViewHolder
import javax.inject.Inject

@AndroidEntryPoint
class UpcomingEventsFragment : BaseFragment<UpcomingEventsViewState, DialogCommand>() {
    override val layout = R.layout.fragment_notifications

    @Inject
    override lateinit var destinationToNavigationMapper: UpcomingEventsFragmentDestinationMapper
    override val viewModel: UpcomingEventsViewModel by viewModels()

    private val pastEventAdapter: ItemsListAdapter<EventUiModel> = ItemsListAdapter { parent, _ ->
        PastEventViewHolder(
            LayoutInflater.from(requireContext())
                .inflate(R.layout.past_event_list_item, parent, false)
        )

    }

    private val upcomingEventsRecyclerView: RecyclerView get() = requireView().findViewById(R.id.upcoming_events)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        upcomingEventsRecyclerView.adapter = pastEventAdapter
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        upcomingEventsRecyclerView.addItemDecoration(divider)
    }

    override fun renderViewState(viewState: UpcomingEventsViewState) {
        super.renderViewState(viewState)
        pastEventAdapter.setAdapterItems(viewState.events)
    }
}
