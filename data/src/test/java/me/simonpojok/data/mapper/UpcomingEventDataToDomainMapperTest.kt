package me.simonpojok.data.mapper

import junit.framework.Assert
import me.simonpojok.data.events.mapper.UpcomingEventDataToDomainMapper
import me.simonpojok.data.events.model.EventDataModel
import me.simonpojok.domain.events.model.EventDomainModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class UpcomingEventDataToDomainMapperTest(
    private val input: EventDataModel.UpcomingEvent,
    private val expectedResult: EventDomainModel.UpcomingEvent
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "Given {0} When toDomain Then returns {1}")
        fun params() = listOf(
            arrayOf(
                EventDataModel.UpcomingEvent(
                    id = "1",
                    title = "Coding",
                    subtitle = "Love Coding",
                    imageUrl = "www.example.com/image.png",
                    date = "22/09/1994"
                ),
                EventDomainModel.UpcomingEvent(
                    id = "1",
                    title = "Coding",
                    subtitle = "Love Coding",
                    imageUrl = "www.example.com/image.png",
                    date = "22/09/1994"
                )
            ),
            arrayOf(
                EventDataModel.UpcomingEvent(
                    id = "2",
                    title = "Coding 2",
                    subtitle = "Love Coding 2",
                    imageUrl = "www.example.com/image.png 2",
                    date = "24/09/1994"
                ),
                EventDomainModel.UpcomingEvent(
                    id = "2",
                    title = "Coding 2",
                    subtitle = "Love Coding 2",
                    imageUrl = "www.example.com/image.png 2",
                    date = "24/09/1994"
                ),
            ),
            arrayOf(
                EventDataModel.UpcomingEvent(
                    id = "3",
                    title = "Coding 3",
                    subtitle = "Love Coding 3",
                    imageUrl = "www.example.com/image3.png",
                    date = "24/03/1994"
                ), EventDomainModel.UpcomingEvent(
                    id = "3",
                    title = "Coding 3",
                    subtitle = "Love Coding 3",
                    imageUrl = "www.example.com/image3.png",
                    date = "24/03/1994"
                )
            )
        )
    }

    lateinit var classUnderTest: UpcomingEventDataToDomainMapper

    @Before
    fun setUp() {
        classUnderTest = UpcomingEventDataToDomainMapper()
    }

    @Test
    fun `Given data Model When toDomain then returns expected domain model`() {
        // When
        val actualResult = classUnderTest.toDomain(input)

        // Then
        Assert.assertEquals(expectedResult, actualResult)
    }
}
