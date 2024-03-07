package sahaj.ai

import java.time.LocalDateTime

class OfficeAdmin(office: Office, private val calendar: Calendar) {

    private val roomsList = office.meetingRooms

    fun bookRoom(
        roomId: MeetingRoom,
        requiredStartTime: LocalDateTime,
        requiredEndTime: LocalDateTime,
        roomOccupantName: String,
        bookingAgenda: String
    ): Boolean {
        for (schedule in calendar.schedules)
            if (schedule.meetingRoom == roomId &&
                calendar.doesTimeOverlap(
                    schedule.startDateTime,
                    schedule.endDateTime,
                    requiredStartTime,
                    requiredEndTime
                )
            ) return false

        calendar.schedules.addLast(
            Schedule(
                roomId,
                requiredStartTime,
                requiredEndTime,
                roomOccupantName,
                bookingAgenda
            )
        )
        return true
    }

    fun viewRoomsVacancyByTimeRange(
        viewingStartTime: LocalDateTime,
        viewingEndTime: LocalDateTime
    ): MutableSet<String> {
        val vacantRoomIds: MutableSet<String> = mutableSetOf()

        for (room in roomsList)
            vacantRoomIds += room.roomId

        for (schedule in calendar.schedules)
            if (calendar.doesTimeOverlap(
                    schedule.startDateTime,
                    schedule.endDateTime,
                    viewingStartTime,
                    viewingEndTime
                )
            ) vacantRoomIds -= schedule.meetingRoom.roomId

        return vacantRoomIds
    }
}