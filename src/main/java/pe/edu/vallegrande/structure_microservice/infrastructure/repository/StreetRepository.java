package pe.edu.vallegrande.structure_microservice.infrastructure.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.edu.vallegrande.structure_microservice.domain.models.Street;
import reactor.core.publisher.Flux;

public interface StreetRepository extends ReactiveMongoRepository<Street, String> {
    Flux<Street> findAllByZoneId(String zoneId);
}

