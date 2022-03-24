package codes.draeger.hotels.api

import codes.draeger.hotels.model.Hotel
import codes.draeger.hotels.model.Review
import codes.draeger.hotels.model.Room
import codes.draeger.hotels.model.RoomStat
import codes.draeger.hotels.repository.HotelRepository
import org.springframework.web.bind.annotation.*

@RestController
class HotelController(
    private val hotelRepository: HotelRepository
) {

    @PostMapping("/add")
    fun addData(
        @RequestBody body: Hotel
    ) {
        hotelRepository.addHotel(body)
    }

    @GetMapping("/all")
    fun getAllHotels() = hotelRepository.listAll()

    @GetMapping("/all/{id}")
    fun getOneHotel(@PathVariable id: String) = hotelRepository.listAll().find { it.id == id }


    @PostMapping("/add-review/{id}")
    fun addReview(
        @PathVariable id: String,
        @RequestBody body: Review
    ) {
        val hotel = hotelRepository.listAll().find { it.id == id } ?: throw IllegalArgumentException("hotel with id $id not known")
        hotel.reviews.add(body)
        hotelRepository.updateHotel(hotel)
    }

    @DeleteMapping("/remove/{id}")
    fun removeHotel(
        @PathVariable id: String
    ) {
        hotelRepository.deleteHotel(id)
    }
    @PostMapping("/add-room/{id}")
    fun addRoom(
        @PathVariable id: String,
        @RequestBody body: Room
    ) {
        val hotel = hotelRepository.listAll().find { it.id == id } ?: throw IllegalArgumentException("hotel with id $id not known")
        hotel.rooms.add(body)
        hotelRepository.updateHotel(hotel)
    }
}
