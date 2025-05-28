package pe.edu.vallegrande.structure_microservice.application.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.structure_microservice.application.services.OrganizationService;
import pe.edu.vallegrande.structure_microservice.domain.models.Organization;
import pe.edu.vallegrande.structure_microservice.infrastructure.dto.request.OrganizationCreateRequest;
import pe.edu.vallegrande.structure_microservice.infrastructure.repository.OrganizationRepository;
import pe.edu.vallegrande.structure_microservice.infrastructure.advice.NotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository repository;

    @Override
    public Flux<Organization> getAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Organization> getById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("No se encontró la organización con ID: " + id)));
    }

    @Override
    public Mono<Organization> save(OrganizationCreateRequest request) {
        Organization organization = Organization.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .legalRepresentative(request.getLegalRepresentative())
                .creationDate(request.getCreationDate() != null ? request.getCreationDate() : LocalDateTime.now())
                .status(request.getStatus() != null ? request.getStatus() : true)
                .registrationDate(request.getRegistrationDate() != null ? request.getRegistrationDate() : LocalDateTime.now())
                .build();

        return repository.save(organization);
    }

    @Override
    public Mono<Organization> update(String id, Organization organization) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("No se encontró la organización con ID: " + id)))
                .flatMap(existing -> {
                    organization.setId(id);  // CORRECCIÓN AQUÍ
                    return repository.save(organization);
                });
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("No se encontró la organización con ID: " + id)))
                .flatMap(repository::delete);
    }

    @Override
    public Mono<Organization> activate(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("No se encontró la organización con ID: " + id)))
                .flatMap(org -> {
                    org.setStatus(true);
                    return repository.save(org);
                });
    }

    @Override
    public Mono<Organization> deactivate(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("No se encontró la organización con ID: " + id)))
                .flatMap(org -> {
                    org.setStatus(false);
                    return repository.save(org);
                });
    }
}
