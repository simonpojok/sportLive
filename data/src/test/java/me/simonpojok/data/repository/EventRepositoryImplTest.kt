package me.simonpojok.data.repository

import com.nhaarman.mockitokotlin2.given
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import me.simonpojok.data.events.mapper.PastEventDataToDomainMapper
import me.simonpojok.data.events.mapper.UpcomingEventDataToDomainMapper
import me.simonpojok.data.events.model.EventDataModel
import me.simonpojok.data.events.remote.EventsService
import me.simonpojok.data.events.repository.EventRepositoryImpl
import me.simonpojok.domain.events.model.EventDomainModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EventRepositoryImplTest {
    private lateinit var classUnderTest: EventRepositoryImpl

    @Mock
    lateinit var upcomingEventDataToDomainMapper: UpcomingEventDataToDomainMapper

    @Mock
    lateinit var pastEventDataToDomainMapper: PastEventDataToDomainMapper

    @Mock
    lateinit var eventsService: EventsService

    @Before
    fun setUp() {
        classUnderTest = EventRepositoryImpl(
            upcomingEventDataToDomainMapper, pastEventDataToDomainMapper, eventsService
        )
    }

    @Test
    fun `When getUpcomingEvents Then returns Upcoming Events`() = runBlocking {
        // Given
        val expectedResults: List<EventDomainModel.UpcomingEvent> = listOf(
            EventDomainModel.UpcomingEvent(
                id = "1",
                title = "Coding",
                subtitle = "Love Coding",
                imageUrl = "www.example.com/image.png",
                date = "22/09/1994"
            ),
            EventDomainModel.UpcomingEvent(
                id = "2",
                title = "Coding 2",
                subtitle = "Love Coding 2",
                imageUrl = "www.example.com/image.png 2",
                date = "24/09/1994"
            ),
            EventDomainModel.UpcomingEvent(
                id = "3",
                title = "Coding 3",
                subtitle = "Love Coding 3",
                imageUrl = "www.example.com/image3.png",
                date = "24/03/1994"
            )
        )

        val expectedData: List<EventDataModel.UpcomingEvent> = listOf(
            EventDataModel.UpcomingEvent(
                id = "1",
                title = "Coding",
                subtitle = "Love Coding",
                imageUrl = "www.example.com/image.png",
                date = "22/09/1994"
            ),
            EventDataModel.UpcomingEvent(
                id = "2",
                title = "Coding 2",
                subtitle = "Love Coding 2",
                imageUrl = "www.example.com/image.png 2",
                date = "24/09/1994"
            ),
            EventDataModel.UpcomingEvent(
                id = "3",
                title = "Coding 3",
                subtitle = "Love Coding 3",
                imageUrl = "www.example.com/image3.png",
                date = "24/03/1994"
            )
        )

        expectedData.forEachIndexed { index, upcomingEvent ->
            given(upcomingEventDataToDomainMapper.toDomain(upcomingEvent)).willReturn(
                expectedResults[index]
            )
        }

        given(eventsService.getUpcomingEvents())
            .willReturn(
                expectedData
            )

        // When
        val actualResult = classUnderTest.getUpcomingEvents()

        // Then
        Assert.assertEquals(expectedResults, actualResult)
    }


    @Test
    fun `When getPastEvents Then returns Upcoming Events`() = runBlocking {
        // Given
        val expectedResults: List<EventDomainModel.PastEvent> = listOf(
            EventDomainModel.PastEvent(
                id = "1",
                title = "Coding",
                subtitle = "Love Coding",
                imageUrl = "www.example.com/image.png",
                date = "22/09/1994",
                videoUrl = "video"
            ),
            EventDomainModel.PastEvent(
                id = "2",
                title = "Coding 2",
                subtitle = "Love Coding 2",
                imageUrl = "www.example.com/image.png 2",
                date = "24/09/1994",
                videoUrl = "video"
            ),
            EventDomainModel.PastEvent(
                id = "3",
                title = "Coding 3",
                subtitle = "Love Coding 3",
                imageUrl = "www.example.com/image3.png",
                date = "24/03/1994",
                videoUrl = "video"
            )
        )

        val expectedData: List<EventDataModel.PastEvent> = listOf(
            EventDataModel.PastEvent(
                id = "1",
                title = "Coding",
                subtitle = "Love Coding",
                imageUrl = "www.example.com/image.png",
                date = "22/09/1994",
                videoUrl = "video"
            ),
            EventDataModel.PastEvent(
                id = "2",
                title = "Coding 2",
                subtitle = "Love Coding 2",
                imageUrl = "www.example.com/image.png 2",
                date = "24/09/1994",
                videoUrl = "video"
            ),
            EventDataModel.PastEvent(
                id = "3",
                title = "Coding 3",
                subtitle = "Love Coding 3",
                imageUrl = "www.example.com/image3.png",
                date = "24/03/1994",
                videoUrl = "video"
            )
        )

        expectedData.forEachIndexed { index, event ->
            given(pastEventDataToDomainMapper.toDomain(event)).willReturn(
                expectedResults[index]
            )
        }

        given(eventsService.getPastEvents())
            .willReturn(
                expectedData
            )

        // When
        val actualResult = classUnderTest.getPastEvents()

        // Then
        Assert.assertEquals(expectedResults, actualResult)
    }
}
