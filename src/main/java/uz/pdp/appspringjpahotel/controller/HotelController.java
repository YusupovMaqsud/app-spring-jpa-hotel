package uz.pdp.appspringjpahotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringjpahotel.entity.Hotel;
import uz.pdp.appspringjpahotel.repository.HotelRepository;
import uz.pdp.appspringjpahotel.repository.RoomRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/getHotel")
    public List<Hotel> getHotel() {
        List<Hotel> hotelList = hotelRepository.findAll();
        return hotelList;
    }


    @GetMapping("/getHotel/{id}")
    public Hotel getHotel(@PathVariable Integer id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            return hotel;
        }
        return null;
    }


    @PostMapping("/addHotel")
    public String addHotel(@RequestBody Hotel hotel) {
        boolean existsByName = hotelRepository.existsByName(hotel.getName());
        if(existsByName){
            return "This Hotel already exist";
        }

        hotelRepository.save(hotel);
        return "Hotel added";
    }


    @DeleteMapping("/deleteHotel/{id}")
    public String deleteHotel(@PathVariable Integer id){
        hotelRepository.deleteById(id);
        return "Hotel deleted";
    }


    @PutMapping("/editHotel/{id}")
    public String editedHotel(@PathVariable Integer id,@RequestBody Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if(optionalHotel.isPresent()){
            Hotel edithotel = optionalHotel.get();
            edithotel.setName(hotel.getName());
            hotelRepository.save(edithotel);
            return "Hotel edited";
        }
        return "Hotel not found";
    }

}
