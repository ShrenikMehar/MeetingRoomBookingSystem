import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import sahaj.ai.*
import java.time.LocalDateTime
import kotlin.test.assertEquals

class AdminTest {

    private lateinit var room101: MeetingRoom
    private lateinit var room201: MeetingRoom
    private lateinit var room301: MeetingRoom
    private lateinit var room401: MeetingRoom

    private lateinit var room102: MeetingRoom
    private lateinit var room202: MeetingRoom
    private lateinit var room302: MeetingRoom
    private lateinit var room402: MeetingRoom

    private lateinit var office: Office
    private lateinit var calendar: Calendar
    private lateinit var officeAdmin: OfficeAdmin

    private val requirements = listOf(
        AvailableAsset.TELEVISION,
        AvailableAsset.CAMERA,
        AvailableAsset.MICROPHONE,
        AvailableAsset.WHITEBOARD
    )

    @BeforeEach
    fun setUp() {
        room101 = MeetingRoom("101",requirements)
        room201 = MeetingRoom("201",requirements)
        room301 = MeetingRoom("301",requirements)
        room401 = MeetingRoom("401",requirements)
        room102 = MeetingRoom("102",requirements)
        room202 = MeetingRoom("202",requirements)
        room302 = MeetingRoom("302",requirements)
        room402 = MeetingRoom("402",requirements)
        office = Office(listOf(room101, room201, room301, room401, room102, room202, room302, room402))
        calendar = Calendar()
        officeAdmin = OfficeAdmin(office, calendar)
    }

    @Test
    fun `Admin should be able to book room 201 for 12 pm to 2 pm`() {
        val meetingStartTime = LocalDateTime.of(2024, 2, 1, 12, 0)
        val meetingEndTime = LocalDateTime.of(2024, 2, 1, 14, 0)
        val meetingConductor = "Shrenik"
        val meetingAgenda = "Why I should get 700% Salary Hike"

        val isBooked = officeAdmin.bookRoom(
            room201,
            meetingStartTime,
            meetingEndTime,
            meetingConductor,
            meetingAgenda
        )

        assertTrue(isBooked)
    }

    @Test
    fun `Admin should be able to book room 201 for 12 pm to 2 pm and 2 pm to 4 pm`() {
        val meetingOneStartTime = LocalDateTime.of(2024, 2, 1, 12, 0)
        val meetingOneEndTime = LocalDateTime.of(2024, 2, 1, 14, 0)
        val meetingOneConductor = "Akash"
        val meetingOneAgenda = "Relationship Advice"

        val meetingTwoStartTime = LocalDateTime.of(2024, 2, 1, 14, 0)
        val meetingTwoEndTime = LocalDateTime.of(2024, 2, 1, 16, 0)
        val meetingTwoConductor = "Sreenivas"
        val meetingTwoAgenda = "Why you should not follow Akash's Relationship Advice"

        officeAdmin.bookRoom(
            room101,
            meetingOneStartTime,
            meetingOneEndTime,
            meetingOneConductor,
            meetingOneAgenda
        )

        val isBooked = officeAdmin.bookRoom(
            room201,
            meetingTwoStartTime,
            meetingTwoEndTime,
            meetingTwoConductor,
            meetingTwoAgenda
        )

        assertTrue(isBooked)
    }

    @Test
    fun `Admin should be not able to book room 201 for 3 pm to 5 pm if room 201 is booked for 2 pm to 4 pm`() {
        val meetingOneStartTime = LocalDateTime.of(2024, 2, 1, 14, 0)
        val meetingOneEndTime = LocalDateTime.of(2024, 2, 1, 16, 0)
        val meetingOneConductor = "Rajit"
        val meetingOneAgenda = "Pecha Kucha Demo to group WITHOUT PRESENTATION OR KEYNOTES"

        val meetingTwoStartTime = LocalDateTime.of(2024, 2, 1, 15, 0)
        val meetingTwoEndTime = LocalDateTime.of(2024, 2, 1, 17, 0)
        val meetingTwoConductor = "Sujitha"
        val meetingTwoAgenda = "Outrageous Feedback to Rajit for not giving Pecha Kucha Demo"

        officeAdmin.bookRoom(
            room201,
            meetingOneStartTime,
            meetingOneEndTime,
            meetingOneConductor,
            meetingOneAgenda
        )
        val isBooked = officeAdmin.bookRoom(
            room201,
            meetingTwoStartTime,
            meetingTwoEndTime,
            meetingTwoConductor,
            meetingTwoAgenda
        )

        assertFalse(isBooked)
    }

    @Test
    fun `Admin should be able to book room 301 for 3 pm to 5 pm if room 201 is booked for 2 pm to 4 pm`() {
        val meetingOneStartTime = LocalDateTime.of(2024, 2, 1, 14, 0)
        val meetingOneEndTime = LocalDateTime.of(2024, 2, 1, 16, 0)
        val meetingOneConductor = "Harshit"
        val meetingOneAgenda = "How to type 69 words per minute (session)"

        val meetingTwoStartTime = LocalDateTime.of(2024, 2, 1, 15, 0)
        val meetingTwoEndTime = LocalDateTime.of(2024, 2, 1, 17, 0)
        val meetingTwoConductor = "Shashank"
        val meetingTwoAgenda = "Why not to type 100 words per minute (realization)"

        officeAdmin.bookRoom(
            room201,
            meetingOneStartTime,
            meetingOneEndTime,
            meetingOneConductor,
            meetingOneAgenda
        )
        val isBooked = officeAdmin.bookRoom(
            room301,
            meetingTwoStartTime,
            meetingTwoEndTime,
            meetingTwoConductor,
            meetingTwoAgenda
        )

        assertTrue(isBooked)
    }

    @Test
    fun `Admin should be able to view rooms free during 2 pm to 4 pm`() {
        val meetingOneStartTime = LocalDateTime.of(2024, 2, 1, 14, 0)
        val meetingOneEndTime = LocalDateTime.of(2024, 2, 1, 16, 0)
        val meetingOneConductor = "Logesh"
        val meetingOneAgenda = "Code Reviewing Pain"

        val meetingTwoStartTime = LocalDateTime.of(2024, 2, 1, 15, 0)
        val meetingTwoEndTime = LocalDateTime.of(2024, 2, 1, 17, 0)
        val meetingTwoConductor = "Karun"
        val meetingTwoAgenda = "Away from Code Reviewing Pain"

        val meetingThreeStartTime = LocalDateTime.of(2024, 2, 1, 10, 0)
        val meetingThreeEndTime = LocalDateTime.of(2024, 2, 1, 12, 0)
        val meetingThreeConductor = "Rhushikesh"
        val meetingThreeAgenda = "Puneet's Interview"

        val meetingFourStartTime = LocalDateTime.of(2024, 2, 1, 16, 0)
        val meetingFourEndTime = LocalDateTime.of(2024, 2, 1, 18, 0)
        val meetingFourConductor = "Komal"
        val meetingFourAgenda = "Lorem ipsum dolor sit amet, consectetur adipiscing elit"

        officeAdmin.bookRoom(
            room201,
            meetingOneStartTime,
            meetingOneEndTime,
            meetingOneConductor,
            meetingOneAgenda
        )
        officeAdmin.bookRoom(
            room202,
            meetingTwoStartTime,
            meetingTwoEndTime,
            meetingTwoConductor,
            meetingTwoAgenda
        )
        officeAdmin.bookRoom(
            room101,
            meetingThreeStartTime,
            meetingThreeEndTime,
            meetingThreeConductor,
            meetingThreeAgenda
        )
        officeAdmin.bookRoom(
            room102,
            meetingFourStartTime,
            meetingFourEndTime,
            meetingFourConductor,
            meetingFourAgenda
        )

        val viewingStartTime = LocalDateTime.of(2024, 2, 1, 14, 0)
        val viewingEndTime = LocalDateTime.of(2024, 2, 1, 16, 0)

        val vacantRoomsList = officeAdmin.viewRoomsVacancyByTimeRange(viewingStartTime, viewingEndTime)

        assertEquals(setOf("101", "102", "301", "302", "401", "402"), vacantRoomsList)
    }

    @Test
    fun `Admin should be able to view the meeting rooms with television and microphone`() {
        val availableAssetForRoom101 = listOf(AvailableAsset.TELEVISION, AvailableAsset.MICROPHONE)
        val availableAssetForRoom201 = emptyList<AvailableAsset>()

        val availableAssetForRoom102 = listOf(
            AvailableAsset.TELEVISION,
            AvailableAsset.CAMERA,
            AvailableAsset.MICROPHONE
        )
        val availableAssetForRoom202 = listOf(
            AvailableAsset.CAMERA,
            AvailableAsset.WHITEBOARD
        )

        val room101 = MeetingRoom("101",availableAssetForRoom101)
        val room201 = MeetingRoom("201",availableAssetForRoom201)
        val room102 = MeetingRoom("102",availableAssetForRoom102)
        val room202 = MeetingRoom("202",availableAssetForRoom202)

        val office = Office(listOf(room101, room201, room102, room202))
        val calendar = Calendar()
        val officeAdmin = OfficeAdmin(office, calendar)

        val meetingOneStartTime = LocalDateTime.of(2024, 2, 1, 14, 0)
        val meetingOneEndTime = LocalDateTime.of(2024, 2, 1, 16, 0)
        val meetingOneConductor = "Logesh"
        val meetingOneAgenda = "Code Reviewing Pain"

        val meetingTwoStartTime = LocalDateTime.of(2024, 2, 1, 15, 0)
        val meetingTwoEndTime = LocalDateTime.of(2024, 2, 1, 17, 0)
        val meetingTwoConductor = "Karun"
        val meetingTwoAgenda = "Away from Code Reviewing Pain"

        val meetingThreeStartTime = LocalDateTime.of(2024, 2, 1, 10, 0)
        val meetingThreeEndTime = LocalDateTime.of(2024, 2, 1, 12, 0)
        val meetingThreeConductor = "Rhushikesh"
        val meetingThreeAgenda = "Puneet's Interview"

        val meetingFourStartTime = LocalDateTime.of(2024, 2, 1, 16, 0)
        val meetingFourEndTime = LocalDateTime.of(2024, 2, 1, 18, 0)
        val meetingFourConductor = "Komal"
        val meetingFourAgenda = "Lorem ipsum dolor sit amet, consectetur adipiscing elit"

        officeAdmin.bookRoom(
            room201,
            meetingOneStartTime,
            meetingOneEndTime,
            meetingOneConductor,
            meetingOneAgenda
        )
        officeAdmin.bookRoom(
            room202,
            meetingTwoStartTime,
            meetingTwoEndTime,
            meetingTwoConductor,
            meetingTwoAgenda
        )
        officeAdmin.bookRoom(
            room101,
            meetingThreeStartTime,
            meetingThreeEndTime,
            meetingThreeConductor,
            meetingThreeAgenda
        )
        officeAdmin.bookRoom(
            room102,
            meetingFourStartTime,
            meetingFourEndTime,
            meetingFourConductor,
            meetingFourAgenda
        )

        val requiredAssets = listOf(AvailableAsset.TELEVISION, AvailableAsset.MICROPHONE)

        val vacantRoomsList = officeAdmin.viewRoomsVacancyByAvailableAsset(requiredAssets)

        assertEquals(setOf("101", "102"), vacantRoomsList)
    }
}