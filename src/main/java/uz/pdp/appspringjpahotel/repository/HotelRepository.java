package uz.pdp.appspringjpahotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appspringjpahotel.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {
    boolean existsByName(String name);
}
