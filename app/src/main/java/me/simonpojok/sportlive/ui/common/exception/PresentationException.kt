package me.simonpojok.sportlive.ui.common.exception

abstract class PresentationException(val throwable: Throwable = Throwable()) : Exception(throwable)
