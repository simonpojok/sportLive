package me.simonpojok.sportlive.ui.common.viewmodel

import androidx.navigation.NavController

interface UiDestination {
    fun navigate(navController: NavController)
}
