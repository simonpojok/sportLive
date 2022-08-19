package me.simonpojok.sportlive.ui.common.viewmodel.exception

abstract class PresentationException(val throwable: Throwable = Throwable()) : Exception(throwable)
