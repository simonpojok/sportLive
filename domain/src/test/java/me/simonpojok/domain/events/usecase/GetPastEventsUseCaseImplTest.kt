package me.simonpojok.domain.events.usecase

import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import me.simonpojok.domain.common.fakeCoroutineContextProvider
import me.simonpojok.domain.common.fakeCoroutineScope
import me.simonpojok.domain.events.model.EventDomainModel.PastEvent
import me.simonpojok.domain.events.repository.EventRepository
import com.nhaarman.mockitokotlin2.given
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPastEventsUseCaseImplTest {
    private lateinit var classUnderTest: GetPastEventsUseCaseImpl

    @Mock
    lateinit var eventRepository: EventRepository

    @Before
    fun setUp() {
        classUnderTest = GetPastEventsUseCaseImpl(
            eventRepository = eventRepository,
            coroutineContextProvider = fakeCoroutineContextProvider
        )
    }

    @Test
    fun `When executeInBackground then returns Past Events`() = runBlocking {
        // Given

        val expectedResult: List<PastEvent> = listOf(
            PastEvent(
                id = "1",
                title = "Coding",
                subtitle = "Love Coding",
                imageUrl = "www.example.com/image.png",
                date = "22/09/1994",
                videoUrl = "www.example.com/video.pm4"
            ),
            PastEvent(
                id = "2",
                title = "Coding 2",
                subtitle = "Love Coding 2",
                imageUrl = "www.example.com/image.png 2",
                date = "24/09/1994",
                videoUrl = "www.example.com/video2.pm4"
            ),
            PastEvent(
                id = "3",
                title = "Coding 3",
                subtitle = "Love Coding 3",
                imageUrl = "www.example.com/image3.png",
                date = "24/03/1994",
                videoUrl = "www.example.com/video3.pm4"
            )
        )

        given(eventRepository.getPastEvents())
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
