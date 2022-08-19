package me.simonpojok.data.mapper

import junit.framework.Assert
import me.simonpojok.data.events.mapper.PastEventDataToDomainMapper
import me.simonpojok.data.events.model.PastEventDataModel
import me.simonpojok.domain.events.model.EventDomainModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class PastEventDataToDomainMapperTest(
    private val input: PastEventDataModel,
    private val expectedResult: EventDomainModel.PastEvent
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "Given {0} When toDomain Then returns {1}")
        fun params() = listOf(
            arrayOf(
                PastEventDataModel(
                    id = "1",
                    title = "Coding",
                    subtitle = "Love Coding",
                    imageUrl = "www.example.com/image.png",
                    date = "22/09/1994",
                    videoUrl = "video1"
                ),
                EventDomainModel.PastEvent(
                    id = "1",
                    title = "Coding",
                    subtitle = "Love Coding",
                    imageUrl = "www.example.com/image.png",
                    date = "22/09/1994",
                    videoUrl = "video1"
                )
            ),
            arrayOf(
                PastEventDataModel(
                    id = "2",
                    title = "Coding 2",
                    subtitle = "Love Coding 2",
                    imageUrl = "www.example.com/image.png 2",
                    date = "24/09/1994",
                    videoUrl = "video2"
                ),
                EventDomainModel.PastEvent(
                    id = "2",
                    title = "Coding 2",
                    subtitle = "Love Coding 2",
                    imageUrl = "www.example.com/image.png 2",
                    date = "24/09/1994",
                    videoUrl = "video2"
                ),
            ),
            arrayOf(
                PastEventDataModel(
                    id = "3",
                    title = "Coding 3",
                    subtitle = "Love Coding 3",
                    imageUrl = "www.example.com/image3.png",
                    date = "24/03/1994",
                    videoUrl = "video3"
                ), EventDomainModel.PastEvent(
                    id = "3",
                    title = "Coding 3",
                    subtitle = "Love Coding 3",
                    imageUrl = "www.example.com/image3.png",
                    date = "24/03/1994",
                    videoUrl = "video3"
                )
            )
        )
    }

    lateinit var classUnderTest: PastEventDataToDomainMapper

    @Before
    fun setUp() {
        classUnderTest = PastEventDataToDomainMapper()
    }

    @Test
    fun `Given data Model When toDomain then returns expected domain model`() {
        // When
        val actualResult = classUnderTest.toDomain(input)

        // Then
        Assert.assertEquals(expectedResult, actualResult)
    }
}
