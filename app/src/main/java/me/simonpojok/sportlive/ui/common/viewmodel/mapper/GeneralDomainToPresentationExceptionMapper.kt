package me.simonpojok.sportlive.ui.common.viewmodel.mapper

import me.simonpojok.domain.common.model.DomainException
import me.simonpojok.sportlive.ui.common.viewmodel.exception.PresentationException

class GeneralDomainToPresentationExceptionMapper :
    DomainToPresentationMapper<DomainException, PresentationException>() {
    override fun map(input: DomainException): PresentationException {
        TODO("Not yet implemented")
    }
}
