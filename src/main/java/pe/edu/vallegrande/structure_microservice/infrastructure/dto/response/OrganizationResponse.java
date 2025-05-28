package pe.edu.vallegrande.structure_microservice.infrastructure.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrganizationResponse {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String legalRepresentative;
    private LocalDateTime creationDate;
    private Boolean status;
    private LocalDateTime registrationDate;
}
