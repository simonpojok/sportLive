package me.simonpojok.sportlive.ui.events.mapper

import me.simonpojok.sportlive.ui.common.viewmodel.mapper.DomainToUiMapper
import java.time.OffsetDateTime
import java.time.ZonedDateTime

class DateTimeUiMapper : DomainToUiMapper<String, ZonedDateTime>() {
    override fun map(input: String): ZonedDateTime =
        OffsetDateTime.parse(input).toZonedDateTime() ?: ZonedDateTime.now()
}
