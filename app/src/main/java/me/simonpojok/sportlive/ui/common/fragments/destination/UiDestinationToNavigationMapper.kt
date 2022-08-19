package me.simonpojok.sportlive.ui.common.fragments.destination

import me.simonpojok.sportlive.ui.common.viewmodel.UiDestination

interface UiDestinationToNavigationMapper {
    fun map(uiDestination: UiDestination): UiDestination
}
