
package pe.edu.vallegrande.structure_microservice.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.structure_microservice.domain.models.Street;
import pe.edu.vallegrande.structure_microservice.infrastructure.repository.StreetRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@RestController
@RequestMapping("/api/streets")
@RequiredArgsConstructor
public class StreetController {

    private final StreetRepository streetRepository;

    @PostMapping
    public Mono<Street> create(@RequestBody Street street) {
        street.setCreatedAt(Instant.now());
        return streetRepository.save(street);
    }

    @GetMapping
    public Flux<Street> findAll() {
        return streetRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Street> findById(@PathVariable String id) {
        return streetRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Mono<Street> update(@PathVariable String id, @RequestBody Street street) {
        return streetRepository.findById(id)
                .flatMap(existing -> {
                    street.setStreetId(id);
                    street.setCreatedAt(existing.getCreatedAt());
                    return streetRepository.save(street);
                });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return streetRepository.deleteById(id);
    }
}
