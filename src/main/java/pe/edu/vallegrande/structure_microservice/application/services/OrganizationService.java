package pe.edu.vallegrande.structure_microservice.application.services;

import pe.edu.vallegrande.structure_microservice.infrastructure.dto.request.OrganizationRequest;
import pe.edu.vallegrande.structure_microservice.infrastructure.dto.response.OrganizationResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrganizationService {
    Mono<OrganizationResponse> create(OrganizationRequest request);
    Flux<OrganizationResponse> findAll();
    Mono<OrganizationResponse> findById(String id);
    Mono<OrganizationResponse> update(String id, OrganizationRequest request);
    Mono<Void> delete(String id);
}

