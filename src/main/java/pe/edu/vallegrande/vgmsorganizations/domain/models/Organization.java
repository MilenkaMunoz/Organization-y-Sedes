package pe.edu.vallegrande.vgmsorganizations.domain.models;
import pe.edu.vallegrande.vgmsorganizations.domain.enums.Constants;
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
@Document("organizations")
public class Organization {

    @Id
    private String organizationId;
    private String organizationCode;
    private String organizationName;
    private String legalRepresentative;
    private String address;
    private String phone;

    private Constants status = Constants.ACTIVE; // Enum en vez de String

    private Instant createdAt;
    private Instant updatedAt;
}
