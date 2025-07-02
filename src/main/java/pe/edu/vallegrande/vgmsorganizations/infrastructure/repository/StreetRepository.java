package pe.edu.vallegrande.vgmsorganizations.infrastructure.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.edu.vallegrande.vgmsorganizations.domain.models.Street;
import reactor.core.publisher.Flux;

public interface StreetRepository extends ReactiveMongoRepository<Street, String> {
    Flux<Street> findAllByZoneId(String zoneId);
}

