import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import sahaj.ai.Calendar
import sahaj.ai.MeetingRoom
import sahaj.ai.Office
import sahaj.ai.OfficeAdmin
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.test.assertEquals

class BookingsViewTest {

    private lateinit var room201: MeetingRoom
    private lateinit var office: Office
    private lateinit var calendar: Calendar
    private lateinit var officeAdmin: OfficeAdmin
    private lateinit var viewingDate: LocalDate

    @BeforeEach
    fun setUp() {
        room201 = MeetingRoom("201")
        office = Office(listOf(room201))
        calendar = Calendar()
        officeAdmin = OfficeAdmin(office, calendar)
        viewingDate = LocalDate.of(2024, 2, 1)
    }

    @Test
    fun `Admin should be able to view all schedule of room 201 for date 1 february by day`() {
        val meetingOneStartTime = LocalDateTime.of(2024, 2, 1, 14, 0)
        val meetingOneEndTime = LocalDateTime.of(2024, 2, 1, 16, 0)
        val meetingOneConductor = "Logesh"
        val meetingOneAgenda = "Code Reviewing Pain"

        val meetingTwoStartTime = LocalDateTime.of(2024, 2, 1, 20, 0)
        val meetingTwoEndTime = LocalDateTime.of(2024, 2, 1, 22, 0)
        val meetingTwoConductor = "Karun"
        val meetingTwoAgenda = "Away from Code Reviewing Pain"

        officeAdmin.bookRoom(
            room201,
            meetingOneStartTime,
            meetingOneEndTime,
            meetingOneConductor,
            meetingOneAgenda
        )
        officeAdmin.bookRoom(
            room201,
            meetingTwoStartTime,
            meetingTwoEndTime,
            meetingTwoConductor,
            meetingTwoAgenda
        )

        val viewingDate = LocalDate.of(2024, 2, 1)

        val bookingsForDay = calendar.viewBookingsByDay(room201, viewingDate)
        val listOfBookings: MutableSet<List<Any>> = mutableSetOf()

        for (booking in bookingsForDay) {
            listOfBookings.add(
                listOf(
                    booking.roomOccupantName,
                    booking.meetingAgenda,
                    booking.startDateTime,
                    booking.endDateTime
                )
            )
        }

        val check: MutableSet<List<Any>> = mutableSetOf(
            listOf(
                "Logesh",
                "Code Reviewing Pain",
                LocalDateTime.of(2024, 2, 1, 14, 0),
                LocalDateTime.of(2024, 2, 1, 16, 0)
            ),
            listOf(
                "Karun",
                "Away from Code Reviewing Pain",
                LocalDateTime.of(2024, 2, 1, 20, 0),
                LocalDateTime.of(2024, 2, 1, 22, 0)
            )
        )

        assertEquals(check, listOfBookings)
    }

    @Test
    fun `Admin should be able to view all schedule of room 201 for date 1 february by day even the one's where end time is next day`() {
        val meetingOneStartTime = LocalDateTime.of(2024, 2, 1, 14, 0)
        val meetingOneEndTime = LocalDateTime.of(2024, 2, 1, 16, 0)
        val meetingOneConductor = "Logesh"
        val meetingOneAgenda = "Code Reviewing Pain"

        val meetingTwoStartTime = LocalDateTime.of(2024, 2, 1, 20, 0)
        val meetingTwoEndTime = LocalDateTime.of(2024, 2, 2, 1, 0)
        val meetingTwoConductor = "Karun"
        val meetingTwoAgenda = "Away from Code Reviewing Pain"

        officeAdmin.bookRoom(
            room201,
            meetingOneStartTime,
            meetingOneEndTime,
            meetingOneConductor,
            meetingOneAgenda
        )
        officeAdmin.bookRoom(
            room201,
            meetingTwoStartTime,
            meetingTwoEndTime,
            meetingTwoConductor,
            meetingTwoAgenda
        )

        val viewingDate = LocalDate.of(2024, 2, 1)

        val bookingsForDay = calendar.viewBookingsByDay(room201, viewingDate)
        val listOfBookings: MutableSet<List<Any>> = mutableSetOf()

        for (booking in bookingsForDay) {
            listOfBookings.add(
                listOf(
                    booking.roomOccupantName,
                    booking.meetingAgenda,
                    booking.startDateTime,
                    booking.endDateTime
                )
            )
        }

        val check: MutableSet<List<Any>> = mutableSetOf(
            listOf(
                "Logesh",
                "Code Reviewing Pain",
                LocalDateTime.of(2024, 2, 1, 14, 0),
                LocalDateTime.of(2024, 2, 1, 16, 0)
            ),
            listOf(
                "Karun",
                "Away from Code Reviewing Pain",
                LocalDateTime.of(2024, 2, 1, 20, 0),
                LocalDateTime.of(2024, 2, 2, 1, 0)
            )
        )

        assertEquals(check, listOfBookings)
    }

    @Test
    fun `Admin should be able to view all schedule of room 201 for date 1 february by day even the one's where start time is previous day`() {
        val meetingOneStartTime = LocalDateTime.of(2024, 2, 1, 14, 0)
        val meetingOneEndTime = LocalDateTime.of(2024, 2, 1, 16, 0)
        val meetingOneConductor = "Logesh"
        val meetingOneAgenda = "Code Reviewing Pain"

        val meetingTwoStartTime = LocalDateTime.of(2024, 1, 31, 20, 0)
        val meetingTwoEndTime = LocalDateTime.of(2024, 2, 1, 1, 0)
        val meetingTwoConductor = "Karun"
        val meetingTwoAgenda = "Away from Code Reviewing Pain"

        officeAdmin.bookRoom(
            room201,
            meetingOneStartTime,
            meetingOneEndTime,
            meetingOneConductor,
            meetingOneAgenda
        )
        officeAdmin.bookRoom(
            room201,
            meetingTwoStartTime,
            meetingTwoEndTime,
            meetingTwoConductor,
            meetingTwoAgenda
        )

        val viewingDate = LocalDate.of(2024, 2, 1)

        val bookingsForDay = calendar.viewBookingsByDay(room201, viewingDate)
        val listOfBookings: MutableSet<List<Any>> = mutableSetOf()

        for (booking in bookingsForDay) {
            listOfBookings.add(
                listOf(
                    booking.roomOccupantName,
                    booking.meetingAgenda,
                    booking.startDateTime,
                    booking.endDateTime
                )
            )
        }

        val check: MutableSet<List<Any>> = mutableSetOf(
            listOf(
                "Logesh",
                "Code Reviewing Pain",
                LocalDateTime.of(2024, 2, 1, 14, 0),
                LocalDateTime.of(2024, 2, 1, 16, 0)
            ),
            listOf(
                "Karun",
                "Away from Code Reviewing Pain",
                LocalDateTime.of(2024, 1, 31, 20, 0),
                LocalDateTime.of(2024, 2, 1, 1, 0)
            )
        )

        assertEquals(check, listOfBookings)
    }

    @Test
    fun `Admin should be able to view all schedule of room 201 for month february`() {
        val meetingOneStartTime = LocalDateTime.of(2024, 2, 1, 14, 0)
        val meetingOneEndTime = LocalDateTime.of(2024, 2, 1, 16, 0)
        val meetingOneConductor = "Logesh"
        val meetingOneAgenda = "Code Reviewing Pain"

        val meetingTwoStartTime = LocalDateTime.of(2024, 2, 4, 20, 0)
        val meetingTwoEndTime = LocalDateTime.of(2024, 2, 4, 22, 0)
        val meetingTwoConductor = "Karun"
        val meetingTwoAgenda = "Away from Code Reviewing Pain"

        officeAdmin.bookRoom(
            room201,
            meetingOneStartTime,
            meetingOneEndTime,
            meetingOneConductor,
            meetingOneAgenda
        )
        officeAdmin.bookRoom(
            room201,
            meetingTwoStartTime,
            meetingTwoEndTime,
            meetingTwoConductor,
            meetingTwoAgenda
        )

        val viewingDate = LocalDate.of(2024, 2, 1)

        val bookingsForDay = calendar.viewBookingsByMonth(room201, viewingDate)
        val listOfBookings: MutableSet<List<Any>> = mutableSetOf()

        for (booking in bookingsForDay) {
            listOfBookings.add(
                listOf(
                    booking.roomOccupantName,
                    booking.meetingAgenda,
                    booking.startDateTime,
                    booking.endDateTime
                )
            )
        }

        val check: MutableSet<List<Any>> = mutableSetOf(
            listOf(
                "Logesh",
                "Code Reviewing Pain",
                LocalDateTime.of(2024, 2, 1, 14, 0),
                LocalDateTime.of(2024, 2, 1, 16, 0)
            ),
            listOf(
                "Karun",
                "Away from Code Reviewing Pain",
                LocalDateTime.of(2024, 2, 4, 20, 0),
                LocalDateTime.of(2024, 2, 4, 22, 0)
            )
        )

        assertEquals(check, listOfBookings)
    }

    @Test
    fun `Admin should be able to view all schedule of room 201 for month february even the one's where end time is next month`() {
        val meetingOneStartTime = LocalDateTime.of(2024, 2, 1, 14, 0)
        val meetingOneEndTime = LocalDateTime.of(2024, 2, 1, 16, 0)
        val meetingOneConductor = "Logesh"
        val meetingOneAgenda = "Code Reviewing Pain"

        val meetingTwoStartTime = LocalDateTime.of(2024, 2, 29, 20, 0)
        val meetingTwoEndTime = LocalDateTime.of(2024, 3, 1, 1, 0)
        val meetingTwoConductor = "Karun"
        val meetingTwoAgenda = "Away from Code Reviewing Pain"

        officeAdmin.bookRoom(
            room201,
            meetingOneStartTime,
            meetingOneEndTime,
            meetingOneConductor,
            meetingOneAgenda
        )
        officeAdmin.bookRoom(
            room201,
            meetingTwoStartTime,
            meetingTwoEndTime,
            meetingTwoConductor,
            meetingTwoAgenda
        )

        val viewingDate = LocalDate.of(2024, 2, 1)

        val bookingsForDay = calendar.viewBookingsByMonth(room201, viewingDate)
        val listOfBookings: MutableSet<List<Any>> = mutableSetOf()

        for (booking in bookingsForDay) {
            listOfBookings.add(
                listOf(
                    booking.roomOccupantName,
                    booking.meetingAgenda,
                    booking.startDateTime,
                    booking.endDateTime
                )
            )
        }

        val check: MutableSet<List<Any>> = mutableSetOf(
            listOf(
                "Logesh",
                "Code Reviewing Pain",
                LocalDateTime.of(2024, 2, 1, 14, 0),
                LocalDateTime.of(2024, 2, 1, 16, 0)
            ),
            listOf(
                "Karun",
                "Away from Code Reviewing Pain",
                LocalDateTime.of(2024, 2, 29, 20, 0),
                LocalDateTime.of(2024, 3, 1, 1, 0)
            )
        )

        assertEquals(check, listOfBookings)
    }

    @Test
    fun `Admin should be able to view all schedule of room 201 for month february even the one's where start time is previous month`() {
        val meetingOneStartTime = LocalDateTime.of(2024, 2, 1, 14, 0)
        val meetingOneEndTime = LocalDateTime.of(2024, 2, 1, 16, 0)
        val meetingOneConductor = "Logesh"
        val meetingOneAgenda = "Code Reviewing Pain"

        val meetingTwoStartTime = LocalDateTime.of(2024, 1, 31, 20, 0)
        val meetingTwoEndTime = LocalDateTime.of(2024, 2, 1, 1, 0)
        val meetingTwoConductor = "Karun"
        val meetingTwoAgenda = "Away from Code Reviewing Pain"

        officeAdmin.bookRoom(
            room201,
            meetingOneStartTime,
            meetingOneEndTime,
            meetingOneConductor,
            meetingOneAgenda
        )
        officeAdmin.bookRoom(
            room201,
            meetingTwoStartTime,
            meetingTwoEndTime,
            meetingTwoConductor,
            meetingTwoAgenda
        )

        val viewingDate = LocalDate.of(2024, 2, 1)

        val bookingsForDay = calendar.viewBookingsByMonth(room201, viewingDate)
        val listOfBookings: MutableSet<List<Any>> = mutableSetOf()

        for (booking in bookingsForDay) {
            listOfBookings.add(
                listOf(
                    booking.roomOccupantName,
                    booking.meetingAgenda,
                    booking.startDateTime,
                    booking.endDateTime
                )
            )
        }

        val check: MutableSet<List<Any>> = mutableSetOf(
            listOf(
                "Logesh",
                "Code Reviewing Pain",
                LocalDateTime.of(2024, 2, 1, 14, 0),
                LocalDateTime.of(2024, 2, 1, 16, 0)
            ),
            listOf(
                "Karun",
                "Away from Code Reviewing Pain",
                LocalDateTime.of(2024, 1, 31, 20, 0),
                LocalDateTime.of(2024, 2, 1, 1, 0)
            )
        )

        assertEquals(check, listOfBookings)
    }

    @Test
    fun `Admin should be able to view all schedule of room 201 for week february`() {
        val meetingOneStartTime = LocalDateTime.of(2024, 2, 1, 14, 0)
        val meetingOneEndTime = LocalDateTime.of(2024, 2, 1, 16, 0)
        val meetingOneConductor = "Logesh"
        val meetingOneAgenda = "Code Reviewing Pain"

        val meetingTwoStartTime = LocalDateTime.of(2024, 2, 4, 20, 0)
        val meetingTwoEndTime = LocalDateTime.of(2024, 2, 4, 22, 0)
        val meetingTwoConductor = "Karun"
        val meetingTwoAgenda = "Away from Code Reviewing Pain"

        officeAdmin.bookRoom(
            room201,
            meetingOneStartTime,
            meetingOneEndTime,
            meetingOneConductor,
            meetingOneAgenda
        )
        officeAdmin.bookRoom(
            room201,
            meetingTwoStartTime,
            meetingTwoEndTime,
            meetingTwoConductor,
            meetingTwoAgenda
        )

        val viewingDate = LocalDate.of(2024, 2, 1)

        val bookingsForDay = calendar.viewBookingsByWeek(room201, viewingDate)
        val listOfBookings: MutableSet<List<Any>> = mutableSetOf()

        for (booking in bookingsForDay) {
            listOfBookings.add(
                listOf(
                    booking.roomOccupantName,
                    booking.meetingAgenda,
                    booking.startDateTime,
                    booking.endDateTime
                )
            )
        }

        val check: MutableSet<List<Any>> = mutableSetOf(
            listOf(
                "Logesh",
                "Code Reviewing Pain",
                LocalDateTime.of(2024, 2, 1, 14, 0),
                LocalDateTime.of(2024, 2, 1, 16, 0)
            ),
            listOf(
                "Karun",
                "Away from Code Reviewing Pain",
                LocalDateTime.of(2024, 2, 4, 20, 0),
                LocalDateTime.of(2024, 2, 4, 22, 0)
            )
        )

        assertEquals(check, listOfBookings)
    }
}