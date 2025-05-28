package pe.edu.vallegrande.structure_microservice.application.services;

import pe.edu.vallegrande.structure_microservice.domain.models.Organization;
import pe.edu.vallegrande.structure_microservice.infrastructure.dto.request.OrganizationCreateRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrganizationService {
    Flux<Organization> getAll();
    Mono<Organization> getById(String id);
    Mono<Organization> save(OrganizationCreateRequest request);
    Mono<Organization> update(String id, Organization organization);
    Mono<Void> delete(String id);
    Mono<Organization> activate(String id);
    Mono<Organization> deactivate(String id);
}
