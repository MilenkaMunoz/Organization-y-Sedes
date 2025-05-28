package pe.edu.vallegrande.structure_microservice.domain.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "organizations")
public class Organization {

    @Id
    private String id;  // MongoDB _id

    private String name;

    private String address;

    private String phone;

    private String legalRepresentative;

    private LocalDateTime creationDate;

    private Boolean status;

    private LocalDateTime registrationDate;

}
