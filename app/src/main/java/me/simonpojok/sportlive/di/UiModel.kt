package me.simonpojok.sportlive.di

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.simonpojok.sportlive.ui.common.TimestampMapper
import me.simonpojok.sportlive.ui.common.viewmodel.mapper.GeneralDomainToUiExceptionMapper
import me.simonpojok.sportlive.ui.events.mapper.DateTimeUiMapper
import me.simonpojok.sportlive.ui.events.mapper.PastEventDomainToUiMapper
import me.simonpojok.sportlive.ui.events.mapper.UpcomingEventDomainToUiMapper
import me.simonpojok.sportlive.ui.events.past_events.PastEventsFragmentDestinationMapper
import me.simonpojok.sportlive.ui.events.upcoming_events.UpcomingEventsFragmentDestinationMapper
import me.simonpojok.sportlive.ui.video.VideoPlayBackUiDestinationToNavigationMapper

@Module
@InstallIn(SingletonComponent::class)
object UiModel {
    @Provides
    @Reusable
    fun providesPastEventDomainToUiMapper(
        dateTimeUiMapper: DateTimeUiMapper,
        timestampMapper: TimestampMapper
    ) = PastEventDomainToUiMapper(dateTimeUiMapper, timestampMapper)

    @Provides
    @Reusable
    fun providesPastEventsFragmentDestinationMapper() = PastEventsFragmentDestinationMapper()

    @Provides
    fun providesGeneralDomainToUiExceptionMapper() = GeneralDomainToUiExceptionMapper()

    @Provides
    fun providesUpcomingEventDomainToUiMapper(
        dateTimeUiMapper: DateTimeUiMapper,
        timestampMapper: TimestampMapper
    ) = UpcomingEventDomainToUiMapper(dateTimeUiMapper, timestampMapper)

    @Provides
    fun providesUpcomingEventsFragmentDestinationMapper() =
        UpcomingEventsFragmentDestinationMapper()

    @Provides
    fun providesDateTimeUiMapper() = DateTimeUiMapper()

    @Provides
    fun providesTimestampMapper(
        resource: Resources
    ) = TimestampMapper(resource)

    @Provides
    fun providesResources(@ApplicationContext application: Context) = application.resources

    @Provides
    fun providesVideoPlayBackUiDestinationToNavigationMapper() = VideoPlayBackUiDestinationToNavigationMapper()
}
