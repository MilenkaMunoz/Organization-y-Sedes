package pe.edu.vallegrande.structure_microservice.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.structure_microservice.domain.models.Street;
import pe.edu.vallegrande.structure_microservice.domain.models.Zone;
import pe.edu.vallegrande.structure_microservice.infrastructure.repository.StreetRepository;
import pe.edu.vallegrande.structure_microservice.infrastructure.repository.ZoneRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@RestController
@RequestMapping("/api/zones")
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneRepository zoneRepository;
    private final StreetRepository streetRepository;

    @PostMapping
    public Mono<Zone> create(@RequestBody Zone zone) {
        zone.setCreatedAt(Instant.now());
        return zoneRepository.save(zone);
    }

    @GetMapping
    public Flux<Zone> findAll() {
        return zoneRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Zone> findById(@PathVariable String id) {
        return zoneRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Mono<Zone> update(@PathVariable String id, @RequestBody Zone zone) {
        return zoneRepository.findById(id)
                .flatMap(existing -> {
                    zone.setZoneId(id);
                    zone.setCreatedAt(existing.getCreatedAt());
                    return zoneRepository.save(zone);
                });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return zoneRepository.deleteById(id);
    }

    // 🔹 Nuevo endpoint: obtener las calles de una zona
    @GetMapping("/{zoneId}/streets")
    public Flux<Street> getStreetsByZone(@PathVariable String zoneId) {
        return streetRepository.findAllByZoneId(zoneId);
    }
}
