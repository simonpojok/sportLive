package me.simonpojok.sportlive.ui.common

import android.content.res.Resources
import me.simonpojok.sportlive.R
import me.simonpojok.sportlive.ui.common.viewmodel.mapper.DomainToUiMapper
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TimestampMapper(
    private val resources: Resources
) : DomainToUiMapper<LocalDateTime, String>() {

    override fun map(input: LocalDateTime): String {
        val now = LocalDateTime.now()
        if (input.dayOfMonth == now.dayOfMonth && input.year == now.year && input.month == now.month) {
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            return resources.getString(R.string.today, input.format(formatter))
        }

        if (input.dayOfMonth == (now.dayOfMonth - 1) && input.year == now.year && input.month == now.month) {
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            return resources.getString(R.string.yesterday, input.format(formatter))
        }

        if (input.dayOfMonth == (now.dayOfMonth + 1) && input.year == now.year && input.month == now.month) {
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            return resources.getString(R.string.tomorrow, input.format(formatter))
        }

        if (input.dayOfMonth == (now.dayOfMonth + 2) && input.year == now.year && input.month == now.month) {
            return resources.getString(R.string.in_two_days)
        }

        if (input.dayOfMonth == (now.dayOfMonth + 3) && input.year == now.year && input.month == now.month) {
            return resources.getString(R.string.in_three_days)
        }

        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        return input.format(formatter)
    }
}
