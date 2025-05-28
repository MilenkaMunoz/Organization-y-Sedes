package pe.edu.vallegrande.structure_microservice.infrastructure.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BranchResponse {
    private String branchId;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String organizationId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
