package codes.draeger.hotels.model

import java.util.*

data class Hotel(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val address: String,
    var rooms: MutableList<Room> = mutableListOf(),
    var reviews: MutableList<Review> = mutableListOf(),
)

enum class RoomStat(){
    FREE,
    NEEDS_CLEANING,
    OCCUPIED
}
data class Room(
    val roomNumber: String,
    var status: RoomStat
)
data class Review(
    val message: String,
    val stars: Int
) {
    init {
        require(stars in 1..5) { "ratings are only allowed to be in range between 1 and 5" }
    }
}
fun aDummyHotel(
    name: String = "Holiday-Inn",
    address: String = "Kigali",
) = Hotel(
    name = name,
    address = address
)
fun aDummyRoom(
    roomNumber: String = "001",
    status: RoomStat = RoomStat.FREE
) = Room(
    roomNumber = roomNumber,
    status = status
)
fun aDummyReview(
    message: String = "Successfully",
    stars: Int = 4
) = Review(
    message = message,
    stars = stars
)