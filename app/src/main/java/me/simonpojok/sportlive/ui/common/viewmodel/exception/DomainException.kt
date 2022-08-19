package me.simonpojok.sportlive.ui.common.viewmodel.exception

abstract class DomainException(open val throwable: Throwable) : Exception(throwable) {
    constructor(message: String) : this(Exception(message))
}
