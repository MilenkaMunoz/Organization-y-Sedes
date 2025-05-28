package pe.edu.vallegrande.structure_microservice.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.structure_microservice.domain.models.Organization;
import pe.edu.vallegrande.structure_microservice.infrastructure.dto.request.OrganizationCreateRequest;
import pe.edu.vallegrande.structure_microservice.application.services.OrganizationService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping
    public Flux<Organization> getAll() {
        return organizationService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Organization> getById(@PathVariable String id) {
        return organizationService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Organization> create(@RequestBody OrganizationCreateRequest request) {
        return organizationService.save(request);
    }

    @PutMapping("/{id}")
    public Mono<Organization> update(@PathVariable String id, @RequestBody Organization organization) {
        return organizationService.update(id, organization);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return organizationService.delete(id);
    }

    @PatchMapping("/{id}/activate")
    public Mono<Organization> activate(@PathVariable String id) {
        return organizationService.activate(id);
    }

    @PatchMapping("/{id}/deactivate")
    public Mono<Organization> deactivate(@PathVariable String id) {
        return organizationService.deactivate(id);
    }
}
