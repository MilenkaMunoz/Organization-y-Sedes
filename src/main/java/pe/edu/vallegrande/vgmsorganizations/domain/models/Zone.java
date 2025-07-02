package pe.edu.vallegrande.vgmsorganizations.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("zones")
public class Zone {
    @Id
    private String zoneId;
    private String organizationId;
    private String zoneCode;
    private String zoneName;
    private String description;
    private String status = "ACTIVE";
    private Instant createdAt;
}
