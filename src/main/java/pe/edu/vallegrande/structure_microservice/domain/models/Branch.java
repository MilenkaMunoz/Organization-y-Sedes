package pe.edu.vallegrande.structure_microservice.domain.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "branches")
public class Branch {
    @Id
    private String branchId;

    private String name;
    private String address;
    private String phone;
    private String email;

    private String organizationId; // Asociación con organización

    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
