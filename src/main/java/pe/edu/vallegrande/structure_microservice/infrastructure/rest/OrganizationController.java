package pe.edu.vallegrande.structure_microservice.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.structure_microservice.domain.models.Street;
import pe.edu.vallegrande.structure_microservice.domain.models.Zone;
import pe.edu.vallegrande.structure_microservice.infrastructure.dto.request.OrganizationRequest;
import pe.edu.vallegrande.structure_microservice.infrastructure.dto.response.OrganizationResponse;
import pe.edu.vallegrande.structure_microservice.infrastructure.repository.StreetRepository;
import pe.edu.vallegrande.structure_microservice.infrastructure.repository.ZoneRepository;
import pe.edu.vallegrande.structure_microservice.infrastructure.service.OrganizationService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;
    private final ZoneRepository zoneRepository;
    private final StreetRepository streetRepository;

    @PostMapping
    public Mono<OrganizationResponse> create(@RequestBody OrganizationRequest request) {
        return organizationService.create(request);
    }

    @GetMapping
    public Flux<OrganizationResponse> findAll() {
        return organizationService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<OrganizationResponse> findById(@PathVariable String id) {
        return organizationService.findById(id);
    }

    @PutMapping("/{id}")
    public Mono<OrganizationResponse> update(@PathVariable String id, @RequestBody OrganizationRequest request) {
        return organizationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return organizationService.delete(id);
    }

    // 🔹 Endpoint 1: Organización con zonas y calles (ya está cubierto por findAll si usas DTO completo)
    @GetMapping("/full")
    public Flux<OrganizationResponse> getFullStructure() {
        return organizationService.findAll();
    }

    // 🔹 Endpoint 2: Calles de una organización
    @GetMapping("/{orgId}/streets")
    public Flux<Street> getStreetsByOrganization(@PathVariable String orgId) {
        return zoneRepository.findAllByOrganizationId(orgId)
                .flatMap(zone -> streetRepository.findAllByZoneId(zone.getZoneId()));
    }
}
