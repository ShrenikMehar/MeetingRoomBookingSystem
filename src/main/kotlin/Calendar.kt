package sahaj.ai

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.TemporalAdjusters

data class Schedule(
    val meetingRoom: MeetingRoom,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val roomOccupantName: String,
    val meetingAgenda: String
)

class Calendar {
    val schedules: MutableList<Schedule> = mutableListOf()

    fun doesTimeOverlap(
        existingStartTime: LocalDateTime,
        existingEndTime: LocalDateTime,
        requiredStartTime: LocalDateTime,
        requiredEndTime: LocalDateTime
    ) = existingStartTime.isBefore(requiredEndTime) && existingEndTime.isAfter(requiredStartTime)

    fun viewBookingsByDay(roomId: MeetingRoom, viewingDate: LocalDate): MutableSet<Schedule> {
        val roomIds: MutableSet<Schedule> = mutableSetOf()

        for (schedule in schedules)
            if ((schedule.startDateTime.dayOfMonth == viewingDate.dayOfMonth && schedule.meetingRoom == roomId) ||
                (schedule.endDateTime.dayOfMonth == viewingDate.dayOfMonth && schedule.meetingRoom == roomId)
            ) roomIds += schedule

        return roomIds
    }

    fun viewBookingsByMonth(roomId: MeetingRoom, viewingDate: LocalDate): MutableSet<Schedule> {
        val roomIds: MutableSet<Schedule> = mutableSetOf()

        for (schedule in schedules)
            if ((schedule.startDateTime.month == viewingDate.month && schedule.meetingRoom == roomId) ||
                (schedule.endDateTime.month == viewingDate.month && schedule.meetingRoom == roomId)
            ) roomIds += schedule

        return roomIds
    }

    fun viewBookingsByWeek(roomId: MeetingRoom, viewingDate: LocalDate): MutableSet<Schedule> {
        val roomIds: MutableSet<Schedule> = mutableSetOf()

        val startOfWeek = viewingDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        val endOfWeek = viewingDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))

        for (schedule in schedules) {
            val startDate = schedule.startDateTime.toLocalDate()
            val endDate = schedule.endDateTime.toLocalDate()

            if ((startDate.isEqual(startOfWeek) || startDate.isAfter(startOfWeek) || startDate == endOfWeek)
                && (endDate.isEqual(endOfWeek) || endDate.isBefore(endOfWeek) || endDate == startOfWeek)
                && schedule.meetingRoom == roomId
            ) roomIds += schedule
        }

        return roomIds
    }
}