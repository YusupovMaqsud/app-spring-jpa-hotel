package uz.pdp.appspringjpahotel.payload;

import lombok.Data;

@Data
public class RoomDto {
    private Integer number;
    private String floor;
    private Integer size;
    private Integer hotelId;

}
