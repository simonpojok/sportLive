package me.simonpojok.sportlive.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.simonpojok.data.repository.EventRepositoryImpl
import me.simonpojok.domain.events.repository.EventRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun providesEventsRepository(): EventRepository = EventRepositoryImpl()
}
