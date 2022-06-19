package uz.pdp.appspringjpahotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringjpahotel.entity.Hotel;
import uz.pdp.appspringjpahotel.entity.Room;
import uz.pdp.appspringjpahotel.payload.RoomDto;
import uz.pdp.appspringjpahotel.repository.HotelRepository;
import uz.pdp.appspringjpahotel.repository.RoomRepository;

import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;


    @GetMapping("/forHotel/{hotelId}")
    public Page<Room> getRoomForHotel(@PathVariable Integer hotelId, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Room> roomPage = roomRepository.findByHotel_Id(hotelId, pageable);
        return roomPage;
    }



    @PostMapping("/addroom")
    public String addRoom(@RequestBody RoomDto roomDto) {
        Room room = new Room();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent()) {
            return "Hotel not found";
        }
        Hotel hotel = optionalHotel.get();
        room.setHotel(hotel);
        roomRepository.save(room);
        return "Room added";
    }




    @DeleteMapping("/deleteroom")
    public String deleteRoom(@PathVariable Integer id){
        roomRepository.deleteById(id);
        return "Room deleted";
    }




    @PutMapping("/editRoom/{id}")
    public String editRoom(@PathVariable Integer id,@RequestBody RoomDto roomDto){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if(optionalRoom.isPresent()){
            Room room = optionalRoom.get();
            room.setNumber(roomDto.getNumber());
            room.setFloor(roomDto.getFloor());
            room.setSize(roomDto.getSize());

            Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
            if (!optionalHotel.isPresent()) {
                return "Hotel not found";
            }
            Hotel hotel = optionalHotel.get();
            room.setHotel(hotel);
            roomRepository.save(room);
            return "Room edited";
        }
        return "Room not found";
    }
}
