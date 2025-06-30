package pe.edu.vallegrande.structure_microservice.infrastructure.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.edu.vallegrande.structure_microservice.domain.models.Zone;
import reactor.core.publisher.Flux;

public interface ZoneRepository extends ReactiveMongoRepository<Zone, String> {
    Flux<Zone> findAllByOrganizationId(String organizationId);
}
