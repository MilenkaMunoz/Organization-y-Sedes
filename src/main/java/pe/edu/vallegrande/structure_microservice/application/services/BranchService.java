package pe.edu.vallegrande.structure_microservice.application.services;

import pe.edu.vallegrande.structure_microservice.domain.models.Branch;
import pe.edu.vallegrande.structure_microservice.infrastructure.dto.request.BranchCreateRequest;
import pe.edu.vallegrande.structure_microservice.infrastructure.dto.response.BranchResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BranchService {
    Flux<Branch> getAll();
    Flux<Branch> getAllActive();
    Flux<Branch> getAllInactive();
    Flux<Branch> getByOrganization(String organizationId);
    Mono<Branch> getById(String id);
    Mono<BranchResponse> save(BranchCreateRequest request);
    Mono<Branch> update(String id, Branch branch);
    Mono<Void> delete(String id);
    Mono<Branch> activate(String id);
    Mono<Branch> deactivate(String id);
}
