package id.ac.ui.cs.eaap.lab.dto.request;

import id.ac.ui.cs.eaap.lab.model.*;

import java.util.List;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRoomRequestDTO {
    
    private Long roomId;
    private String roomNumber;
    private String roomName;
    private String buildingName;
    private String faculty;

}
