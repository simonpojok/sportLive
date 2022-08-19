package me.simonpojok.sportlive.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.simonpojok.domain.common.DispatchersCoroutineContextProvider
import me.simonpojok.domain.common.usecase.CoroutineContextProvider
import me.simonpojok.domain.common.usecase.UseCaseExecutor
import me.simonpojok.domain.common.usecaseexecutor.UseCaseExecutorProvider
import me.simonpojok.domain.events.repository.EventRepository
import me.simonpojok.domain.events.usecase.GetPastEventsUseCase
import me.simonpojok.domain.events.usecase.GetPastEventsUseCaseImpl
import me.simonpojok.domain.events.usecase.GetUpcomingEventsUseCase
import me.simonpojok.domain.events.usecase.GetUpcomingEventsUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Reusable
    fun provideCoroutineContextProvider(): CoroutineContextProvider =
        DispatchersCoroutineContextProvider()

    @Provides
    @Reusable
    fun providesUseCaseExecutorProvider(): UseCaseExecutorProvider =
        { coroutineScope -> UseCaseExecutor(coroutineScope) }

    @Provides
    fun providesGetPastEventsUseCase(
        eventRepository: EventRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetPastEventsUseCase = GetPastEventsUseCaseImpl(eventRepository, coroutineContextProvider)

    @Provides
    fun providesGetUpcomingEventsUseCase(
        eventRepository: EventRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetUpcomingEventsUseCase = GetUpcomingEventsUseCaseImpl(
        eventRepository, coroutineContextProvider
    )
}
