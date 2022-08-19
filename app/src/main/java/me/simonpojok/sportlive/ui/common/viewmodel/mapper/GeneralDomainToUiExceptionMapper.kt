package me.simonpojok.sportlive.ui.common.viewmodel.mapper

import me.simonpojok.domain.common.model.DomainException
import me.simonpojok.sportlive.ui.common.viewmodel.exception.UiException
class ErrorDomain: UiException()
class GeneralDomainToUiExceptionMapper :
    DomainToUiMapper<DomainException, UiException>() {
    override fun map(input: DomainException): UiException {
        return ErrorDomain()
    }
}
