package me.simonpojok.domain.events.usecase

import com.nhaarman.mockitokotlin2.given
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import me.simonpojok.domain.common.fakeCoroutineContextProvider
import me.simonpojok.domain.common.fakeCoroutineScope
import me.simonpojok.domain.events.model.EventDomainModel.UpcomingEvent
import me.simonpojok.domain.events.repository.EventRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetUpcomingEventsUseCaseImplTest {
    private lateinit var classUnderTest: GetUpcomingEventsUseCaseImpl

    @Mock
    lateinit var eventRepository: EventRepository

    @Before
    fun setUp() {
        classUnderTest = GetUpcomingEventsUseCaseImpl(
            eventRepository = eventRepository,
            coroutineContextProvider = fakeCoroutineContextProvider
        )
    }

    @Test
    fun `When executeInBackground then returns Past Events`() = runBlocking {
        // Given

        val expectedResult: List<UpcomingEvent> = listOf(
            UpcomingEvent(
                id = "1",
                title = "Coding",
                subtitle = "Love Coding",
                imageUrl = "www.example.com/image.png",
                date = "22/09/1994"
            ),
            UpcomingEvent(
                id = "2",
                title = "Coding 2",
                subtitle = "Love Coding 2",
                imageUrl = "www.example.com/image.png 2",
                date = "24/09/1994"
            ),
            UpcomingEvent(
                id = "3",
                title = "Coding 3",
                subtitle = "Love Coding 3",
                imageUrl = "www.example.com/image3.png",
                date = "24/03/1994"
            )
        )

        given(eventRepository.getUpcomingEvents())
            .willReturn(
                expectedResult
            )

        // When
        val actualResult = classUnderTest.executeInBackground(
            request = Unit,
            coroutineScope = fakeCoroutineScope
        )

        // Then
        Assert.assertEquals(expectedResult, actualResult)
    }
}
