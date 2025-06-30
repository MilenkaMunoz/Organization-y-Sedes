package pe.edu.vallegrande.structure_microservice.domain.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("streets")
public class Street {
    @Id
    private String streetId;
    private String zoneId;
    private String streetCode;
    private String streetName;
    private String streetType;
    private String status = "ACTIVE";
    private Instant createdAt;
}
