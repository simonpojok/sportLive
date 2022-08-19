package me.simonpojok.sportlive.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.simonpojok.data.events.mapper.PastEventDataToDomainMapper
import me.simonpojok.data.events.mapper.UpcomingEventDataToDomainMapper
import me.simonpojok.data.events.remote.EventsService
import me.simonpojok.data.events.repository.EventRepositoryImpl
import me.simonpojok.domain.events.repository.EventRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun providesEventsRepository(
        upcomingEventDataToDomainMapper: UpcomingEventDataToDomainMapper,
        pastEventDataToDomainMapper: PastEventDataToDomainMapper,
        eventsService: EventsService
    ): EventRepository = EventRepositoryImpl(
        upcomingEventDataToDomainMapper,
        pastEventDataToDomainMapper,
        eventsService
    )

    @Provides
    fun providesEventDataToDomainMapper() = UpcomingEventDataToDomainMapper()

    @Provides
    fun providesPastEventDataToDomainMapper() = PastEventDataToDomainMapper()

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
//        val logging = HttpLoggingInterceptor()
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val httpClient = OkHttpClient.Builder()
//        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl("https://us-central1-dazn-sandbox.cloudfunctions.net/")
            .addConverterFactory(GsonConverterFactory.create())
//            .client(httpClient.build())
            .build()
    }

    @Provides
    @Singleton
    fun providesEventsService(retrofit: Retrofit) = retrofit.create(EventsService::class.java)
}
