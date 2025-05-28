package pe.edu.vallegrande.structure_microservice.infrastructure.dto.request;

import lombok.Data;

@Data
public class BranchCreateRequest {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String organizationId;
}
