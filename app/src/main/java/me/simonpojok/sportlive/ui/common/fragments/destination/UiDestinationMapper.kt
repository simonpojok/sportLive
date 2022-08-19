package me.simonpojok.sportlive.ui.common.fragments.destination

import me.simonpojok.sportlive.ui.common.viewmodel.PresentationDestination

interface UiDestinationMapper {
    fun map(presentationDestination: PresentationDestination): UiDestination

    fun throwInvalidDestinationException(currentDestinationClass: PresentationDestination): Nothing =
        throw IllegalArgumentException(
            "Destination ${currentDestinationClass::class.java.name} is not supported. " +
                "Please specify appropriate mapper"
        )
}
