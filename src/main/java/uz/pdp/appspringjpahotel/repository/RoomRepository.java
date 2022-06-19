package uz.pdp.appspringjpahotel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appspringjpahotel.entity.Room;

public interface RoomRepository extends JpaRepository<Room,Integer> {
Page<Room> findByHotel_Id(Integer hotel_id, Pageable pageable);
}
