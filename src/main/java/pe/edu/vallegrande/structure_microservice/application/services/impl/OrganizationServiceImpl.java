package pe.edu.vallegrande.structure_microservice.application.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.structure_microservice.domain.enums.Constants;
import pe.edu.vallegrande.structure_microservice.domain.models.Organization;
import pe.edu.vallegrande.structure_microservice.infrastructure.dto.request.OrganizationRequest;
import pe.edu.vallegrande.structure_microservice.infrastructure.dto.response.*;
import pe.edu.vallegrande.structure_microservice.infrastructure.repository.*;
import pe.edu.vallegrande.structure_microservice.infrastructure.service.OrganizationService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl extends OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final ZoneRepository zoneRepository;
    private final StreetRepository streetRepository;

    @Override
    public Mono<OrganizationResponse> create(OrganizationRequest request) {
        Organization organization = Organization.builder()
                .organizationCode(request.getOrganizationCode())
                .organizationName(request.getOrganizationName())
                .legalRepresentative(request.getLegalRepresentative())
                .address(request.getAddress())
                .phone(request.getPhone())
                .status(Constants.ACTIVE) // Estado por defecto
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        return organizationRepository.save(organization)
                .map(saved -> OrganizationResponse.builder()
                        .organizationId(saved.getOrganizationId())
                        .organizationCode(saved.getOrganizationCode())
                        .organizationName(saved.getOrganizationName())
                        .legalRepresentative(saved.getLegalRepresentative())
                        .address(saved.getAddress())
                        .phone(saved.getPhone())
                        .status(saved.getStatus())
                        .zones(List.of())
                        .build());
    }

    @Override
    public Flux<OrganizationResponse> findAll() {
        return organizationRepository.findAllByStatus(Constants.ACTIVE) // Mostrar solo activos
                .flatMap(this::mapToOrganizationResponse);
    }

    @Override
    public Mono<OrganizationResponse> findById(String id) {
        return organizationRepository.findById(id)
                .flatMap(this::mapToOrganizationResponse);
    }

    @Override
    public Mono<OrganizationResponse> update(String id, OrganizationRequest request) {
        return organizationRepository.findById(id)
                .flatMap(existing -> {
                    existing.setOrganizationCode(request.getOrganizationCode());
                    existing.setOrganizationName(request.getOrganizationName());
                    existing.setLegalRepresentative(request.getLegalRepresentative());
                    existing.setAddress(request.getAddress());
                    existing.setPhone(request.getPhone());
                    existing.setUpdatedAt(Instant.now());
                    return organizationRepository.save(existing);
                })
                .flatMap(this::mapToOrganizationResponse);
    }

    @Override
    public Mono<Void> delete(String id) {
        return organizationRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Organización no encontrada")))
                .flatMap(org -> {
                    org.setStatus(Constants.INACTIVE); // Soft delete
                    org.setUpdatedAt(Instant.now());
                    return organizationRepository.save(org);
                })
                .then();
    }

    private Mono<OrganizationResponse> mapToOrganizationResponse(Organization org) {
        return zoneRepository.findAllByOrganizationId(org.getOrganizationId())
                .flatMap(zone -> streetRepository.findAllByZoneId(zone.getZoneId())
                        .map(street -> StreetResponse.builder()
                                .streetId(street.getStreetId())
                                .streetCode(street.getStreetCode())
                                .streetName(street.getStreetName())
                                .streetType(street.getStreetType())
                                .status(street.getStatus())
                                .build())
                        .collectList()
                        .map(streets -> ZoneResponse.builder()
                                .zoneId(zone.getZoneId())
                                .zoneCode(zone.getZoneCode())
                                .zoneName(zone.getZoneName())
                                .description(zone.getDescription())
                                .status(zone.getStatus())
                                .streets(streets)
                                .build()))
                .collectList()
                .map(zones -> OrganizationResponse.builder()
                        .organizationId(org.getOrganizationId())
                        .organizationCode(org.getOrganizationCode())
                        .organizationName(org.getOrganizationName())
                        .legalRepresentative(org.getLegalRepresentative())
                        .address(org.getAddress())
                        .phone(org.getPhone())
                        .status(org.getStatus())
                        .zones(zones)
                        .build());
    }
}
