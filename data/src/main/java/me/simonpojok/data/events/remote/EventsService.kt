package me.simonpojok.data.events.remote

import me.simonpojok.data.events.model.EventDataModel.PastEvent
import me.simonpojok.data.events.model.EventDataModel.UpcomingEvent
import retrofit2.http.GET

interface EventsService {
    @GET("/getEvents")
    suspend fun getPastEvents(): List<PastEvent>

    @GET("/getSchedule")
    suspend fun getUpcomingEvents(): List<UpcomingEvent>
}
