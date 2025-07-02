package pe.edu.vallegrande.vgmsorganizations.infrastructure.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StreetResponse {
    private String streetId;
    private String streetCode;
    private String streetName;
    private String streetType;
    private String status;
}
