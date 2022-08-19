package me.simonpojok.sportlive.ui.common.mapper

import me.simonpojok.domain.common.model.DomainException
import me.simonpojok.sportlive.ui.common.exception.PresentationException

class GeneralDomainToPresentationExceptionMapper :
    DomainToPresentationMapper<DomainException, PresentationException>() {
    override fun map(input: DomainException): PresentationException {
        TODO("Not yet implemented")
    }
}
