package pe.edu.vallegrande.structure_microservice.application.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.structure_microservice.application.services.BranchService;
import pe.edu.vallegrande.structure_microservice.domain.enums.Constants;
import pe.edu.vallegrande.structure_microservice.domain.models.Branch;
import pe.edu.vallegrande.structure_microservice.infrastructure.dto.request.BranchCreateRequest;
import pe.edu.vallegrande.structure_microservice.infrastructure.dto.response.BranchResponse;
import pe.edu.vallegrande.structure_microservice.infrastructure.exception.CustomException;
import pe.edu.vallegrande.structure_microservice.infrastructure.repository.BranchRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    @Override
    public Flux<Branch> getAll() {
        return branchRepository.findAll();
    }

    @Override
    public Flux<Branch> getAllActive() {
        return branchRepository.findAllByStatus(Constants.ACTIVE.name());
    }

    @Override
    public Flux<Branch> getAllInactive() {
        return branchRepository.findAllByStatus(Constants.INACTIVE.name());
    }

    @Override
    public Flux<Branch> getByOrganization(String organizationId) {
        return branchRepository.findAllByOrganizationId(organizationId);
    }

    @Override
    public Mono<Branch> getById(String id) {
        return branchRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomException(404, "Sucursal no encontrada", "ID: " + id)));
    }

    @Override
    public Mono<BranchResponse> save(BranchCreateRequest request) {
        Branch branch = Branch.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .email(request.getEmail())
                .organizationId(request.getOrganizationId())
                .status(Constants.ACTIVE.name())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return branchRepository.save(branch)
                .map(this::toResponse);
    }

    @Override
    public Mono<Branch> update(String id, Branch branchUpdate) {
        return getById(id)
                .flatMap(branch -> {
                    branch.setName(branchUpdate.getName());
                    branch.setAddress(branchUpdate.getAddress());
                    branch.setPhone(branchUpdate.getPhone());
                    branch.setEmail(branchUpdate.getEmail());
                    branch.setUpdatedAt(LocalDateTime.now());
                    return branchRepository.save(branch);
                });
    }

    @Override
    public Mono<Void> delete(String id) {
        return branchRepository.deleteById(id);
    }

    @Override
    public Mono<Branch> activate(String id) {
        return getById(id).flatMap(branch -> {
            branch.setStatus(Constants.ACTIVE.name());
            branch.setUpdatedAt(LocalDateTime.now());
            return branchRepository.save(branch);
        });
    }

    @Override
    public Mono<Branch> deactivate(String id) {
        return getById(id).flatMap(branch -> {
            branch.setStatus(Constants.INACTIVE.name());
            branch.setUpdatedAt(LocalDateTime.now());
            return branchRepository.save(branch);
        });
    }

    private BranchResponse toResponse(Branch branch) {
        return BranchResponse.builder()
                .branchId(branch.getBranchId())
                .name(branch.getName())
                .address(branch.getAddress())
                .phone(branch.getPhone())
                .email(branch.getEmail())
                .organizationId(branch.getOrganizationId())
                .status(branch.getStatus())
                .createdAt(branch.getCreatedAt())
                .updatedAt(branch.getUpdatedAt())
                .build();
    }
}
