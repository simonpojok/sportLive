package me.simonpojok.sportlive.ui.common

import me.simonpojok.sportlive.ui.common.exception.Error
import me.simonpojok.sportlive.ui.common.exception.Error.None

open class DialogCommand {
    open var show: Boolean = false
    var error: Error = None
}
