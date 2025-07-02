package pe.edu.vallegrande.vgmsorganizations.infrastructure.dto.request;


import lombok.Data;

@Data
public class OrganizationRequest {
    private String organizationCode;
    private String organizationName;
    private String legalRepresentative;
    private String address;
    private String phone;
}
