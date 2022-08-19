package me.simonpojok.sportlive.ui.events.past_events

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
import javax.inject.Inject

@AndroidEntryPoint
class PastEventsFragment : BaseFragment<PastEventsViewState, DialogCommand>() {
    override val layout = R.layout.fragment_past_events
    private val pastEventsRecyclerView: RecyclerView get() = requireView().findViewById(R.id.past_events)

    @Inject
    override lateinit var destinationToNavigationMapper: PastEventsFragmentDestinationMapper

    override val viewModel: PastEventsViewModel by viewModels()

    private val pastEventAdapter: ItemsListAdapter<EventUiModel> = ItemsListAdapter { parent, _ ->
        PastEventViewHolder(
            LayoutInflater.from(requireContext()).inflate(R.layout.past_event_list_item, parent, false)
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pastEventsRecyclerView.adapter = pastEventAdapter
        val divider = DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL)
        pastEventsRecyclerView.addItemDecoration(divider)
    }

    override fun renderViewState(viewState: PastEventsViewState) {
        super.renderViewState(viewState)
        pastEventAdapter.setAdapterItems(viewState.events)
    }
}
