package me.simonpojok.sportlive.ui.common.viewmodel

import me.simonpojok.sportlive.ui.common.viewmodel.exception.Error
import me.simonpojok.sportlive.ui.common.viewmodel.exception.Error.None

open class DialogCommand {
    open var show: Boolean = false
    var error: Error = None
}
