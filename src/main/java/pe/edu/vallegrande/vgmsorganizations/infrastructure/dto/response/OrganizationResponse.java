package pe.edu.vallegrande.vgmsorganizations.infrastructure.dto.response;

import lombok.Builder;
import lombok.Data;
import pe.edu.vallegrande.vgmsorganizations.domain.enums.Constants;

import java.util.List;

@Data
@Builder
public class OrganizationResponse {
    private String organizationId;
    private String organizationCode;
    private String organizationName;
    private String legalRepresentative;
    private String address;
    private String phone;
    private Constants status; // ✅ Tipo corregido
    private List<ZoneResponse> zones;
}
