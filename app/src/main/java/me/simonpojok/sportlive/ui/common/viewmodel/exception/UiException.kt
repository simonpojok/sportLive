package me.simonpojok.sportlive.ui.common.viewmodel.exception

abstract class UiException(val throwable: Throwable = Throwable()) : Exception(throwable)
