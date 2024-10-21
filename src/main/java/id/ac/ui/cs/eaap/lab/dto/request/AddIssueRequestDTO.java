package id.ac.ui.cs.eaap.lab.dto.request;

import id.ac.ui.cs.eaap.lab.model.*;

import java.util.List;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddIssueRequestDTO {
    
    private long issueId;
    private String description;
    private String reportedBy;
    private Date reportedOn;
    private String status;
    private RoomModel roomModel;

}
