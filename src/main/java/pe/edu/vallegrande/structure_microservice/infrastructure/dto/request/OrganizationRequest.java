package pe.edu.vallegrande.structure_microservice.infrastructure.dto.request;


import lombok.Data;

@Data
public class OrganizationRequest {
    private String organizationCode;
    private String organizationName;
    private String legalRepresentative;
    private String address;
    private String phone;
}
