package bitc.aws402.miniproject.dto;

import lombok.Data;

//  추형호 -----
@Data
public class ReservationDTO {
    private Long id;
    private String userId;
    private String accommodationName;
    private String checkIn;
    private String checkOut;
    private String status;
}
