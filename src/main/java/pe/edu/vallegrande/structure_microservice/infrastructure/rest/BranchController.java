package pe.edu.vallegrande.structure_microservice.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.structure_microservice.application.services.BranchService;
import pe.edu.vallegrande.structure_microservice.domain.models.Branch;
import pe.edu.vallegrande.structure_microservice.infrastructure.dto.request.BranchCreateRequest;
import pe.edu.vallegrande.structure_microservice.infrastructure.dto.response.BranchResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @GetMapping
    public Flux<Branch> getAll() {
        return branchService.getAll();
    }

    @GetMapping("/active")
    public Flux<Branch> getAllActive() {
        return branchService.getAllActive();
    }

    @GetMapping("/inactive")
    public Flux<Branch> getAllInactive() {
        return branchService.getAllInactive();
    }

    @GetMapping("/organization/{organizationId}")
    public Flux<Branch> getByOrganization(@PathVariable String organizationId) {
        return branchService.getByOrganization(organizationId);
    }

    @GetMapping("/{id}")
    public Mono<Branch> getById(@PathVariable String id) {
        return branchService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BranchResponse> create(@RequestBody BranchCreateRequest request) {
        return branchService.save(request);
    }

    @PutMapping("/{id}")
    public Mono<Branch> update(@PathVariable String id, @RequestBody Branch branch) {
        return branchService.update(id, branch);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return branchService.delete(id);
    }

    @PatchMapping("/{id}/activate")
    public Mono<Branch> activate(@PathVariable String id) {
        return branchService.activate(id);
    }

    @PatchMapping("/{id}/deactivate")
    public Mono<Branch> deactivate(@PathVariable String id) {
        return branchService.deactivate(id);
    }
}
