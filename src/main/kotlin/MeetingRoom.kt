package sahaj.ai

enum class AvailableAsset {
    TELEVISION,
    MICROPHONE,
    CAMERA,
    WHITEBOARD
}

data class MeetingRoom(val roomId: String, val requirements: List<AvailableAsset>)