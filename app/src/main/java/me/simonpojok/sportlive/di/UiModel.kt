package me.simonpojok.sportlive.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.simonpojok.sportlive.ui.common.viewmodel.mapper.GeneralDomainToUiExceptionMapper
import me.simonpojok.sportlive.ui.events.mapper.PastEventDomainToUiMapper
import me.simonpojok.sportlive.ui.events.mapper.UpcomingEventDomainToUiMapper
import me.simonpojok.sportlive.ui.events.past_events.PastEventsFragmentDestinationMapper
import me.simonpojok.sportlive.ui.events.upcoming_events.UpcomingEventsFragmentDestinationMapper

@Module
@InstallIn(SingletonComponent::class)
object UiModel {
    @Provides
    @Reusable
    fun providesPastEventDomainToUiMapper() = PastEventDomainToUiMapper()

    @Provides
    @Reusable
    fun providesPastEventsFragmentDestinationMapper() = PastEventsFragmentDestinationMapper()

    @Provides
    fun providesGeneralDomainToUiExceptionMapper() = GeneralDomainToUiExceptionMapper()

    @Provides
    fun providesUpcomingEventDomainToUiMapper() = UpcomingEventDomainToUiMapper()

    @Provides
    fun providesUpcomingEventsFragmentDestinationMapper() = UpcomingEventsFragmentDestinationMapper()
}
