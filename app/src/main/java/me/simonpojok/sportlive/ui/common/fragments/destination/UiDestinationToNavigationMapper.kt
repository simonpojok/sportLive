package me.simonpojok.sportlive.ui.common.fragments.destination

import me.simonpojok.sportlive.ui.common.viewmodel.Destination
import me.simonpojok.sportlive.ui.common.viewmodel.UiDestination

interface UiDestinationToNavigationMapper {
    fun map(destination: Destination): UiDestination
}
