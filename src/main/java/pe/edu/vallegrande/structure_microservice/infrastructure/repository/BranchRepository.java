package pe.edu.vallegrande.structure_microservice.infrastructure.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.edu.vallegrande.structure_microservice.domain.models.Branch;
import reactor.core.publisher.Flux;

public interface BranchRepository extends ReactiveMongoRepository<Branch, String> {
    Flux<Branch> findAllByOrganizationId(String organizationId);
    Flux<Branch> findAllByStatus(String status);
}
