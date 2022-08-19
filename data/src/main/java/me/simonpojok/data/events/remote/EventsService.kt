package me.simonpojok.data.events.remote

import me.simonpojok.data.events.model.PastEventDataModel
import me.simonpojok.data.events.model.UpcomingEventDataModel
import retrofit2.http.GET

interface EventsService {
    @GET("/getEvents")
    suspend fun getPastEvents(): List<PastEventDataModel>

    @GET("/getSchedule")
    suspend fun getUpcomingEvents(): List<UpcomingEventDataModel>
}
