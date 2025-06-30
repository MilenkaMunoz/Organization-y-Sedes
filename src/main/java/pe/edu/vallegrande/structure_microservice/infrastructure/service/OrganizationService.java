package pe.edu.vallegrande.structure_microservice.infrastructure.service;

import pe.edu.vallegrande.structure_microservice.infrastructure.dto.request.OrganizationRequest;
import pe.edu.vallegrande.structure_microservice.infrastructure.dto.response.OrganizationResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class OrganizationService {
    public abstract Mono<OrganizationResponse> create(OrganizationRequest request);

    public abstract Flux<OrganizationResponse> findAll();

    public abstract Mono<OrganizationResponse> findById(String id);

    public abstract Mono<OrganizationResponse> update(String id, OrganizationRequest request);

    public abstract Mono<Void> delete(String id);
}
