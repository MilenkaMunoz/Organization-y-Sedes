package pe.edu.vallegrande.vgmsorganizations.infrastructure.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.edu.vallegrande.vgmsorganizations.domain.enums.Constants;
import pe.edu.vallegrande.vgmsorganizations.domain.models.Organization;
import reactor.core.publisher.Flux;

public interface OrganizationRepository extends ReactiveMongoRepository<Organization, String> {

    // Nuevo método para filtrar por estado (ej. ACTIVE o INACTIVE)
    Flux<Organization> findAllByStatus(Constants status);

}
