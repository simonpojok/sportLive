package me.simonpojok.sportlive.ui.common.fragments.mapper

import me.simonpojok.sportlive.ui.common.viewmodel.mapper.PresentationMapperException
import me.simonpojok.sportlive.ui.common.viewmodel.mapper.UiMapperException

abstract class PresentationToUiMapper<INPUT : Any, OUTPUT : Any> {

    fun toUi(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw UiMapperException("Could not map ${input::class.simpleName}", throwable)
    }

    protected abstract fun map(input: INPUT): OUTPUT
}

abstract class UiToPresentationMapper<INPUT : Any, OUTPUT : Any> {

    fun toPresentation(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw PresentationMapperException("Could not map ${input::class.simpleName}", throwable)
    }

    protected abstract fun map(input: INPUT): OUTPUT
}
