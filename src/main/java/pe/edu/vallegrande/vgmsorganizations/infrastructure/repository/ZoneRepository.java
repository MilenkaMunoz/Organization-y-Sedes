package pe.edu.vallegrande.vgmsorganizations.infrastructure.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.edu.vallegrande.vgmsorganizations.domain.models.Zone;
import reactor.core.publisher.Flux;

public interface ZoneRepository extends ReactiveMongoRepository<Zone, String> {
    Flux<Zone> findAllByOrganizationId(String organizationId);
}
