package me.simonpojok.sportlive.ui.common.viewmodel.mapper

class UiMapperException(message: String, throwable: Throwable? = null) :
    Exception(message, throwable)

abstract class DomainToUiMapper<INPUT : Any, OUTPUT : Any> {

    fun toUi(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw UiMapperException("Could not map ${input::class.simpleName}", throwable)
    }

    protected abstract fun map(input: INPUT): OUTPUT
}
