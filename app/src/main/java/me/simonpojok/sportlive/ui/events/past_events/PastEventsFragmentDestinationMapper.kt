package me.simonpojok.sportlive.ui.events.past_events

import androidx.navigation.NavController
import me.simonpojok.sportlive.ui.common.fragments.destination.UiDestinationToNavigationMapper
import me.simonpojok.sportlive.ui.common.viewmodel.Destination
import me.simonpojok.sportlive.ui.common.viewmodel.UiDestination
import me.simonpojok.sportlive.ui.events.past_events.PastEventsFragmentDirections.navigationPastEventsVideoPlayback
import me.simonpojok.sportlive.ui.events.past_events.destinations.VideoPlayBackDestination

class PastEventsFragmentDestinationMapper : UiDestinationToNavigationMapper {
    override fun map(destination: Destination) = when (destination) {
        is VideoPlayBackDestination -> VideoPlayBackUiDestination(destination.videoUrl)
        else -> throw IllegalArgumentException("No Destination Found")
    }

    data class VideoPlayBackUiDestination(private val url: String) : UiDestination {
        override fun navigate(navController: NavController) {
            navController.navigate(navigationPastEventsVideoPlayback(url))
        }
    }
}
