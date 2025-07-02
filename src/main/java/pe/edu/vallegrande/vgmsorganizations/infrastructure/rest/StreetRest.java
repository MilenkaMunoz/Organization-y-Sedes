package pe.edu.vallegrande.vgmsorganizations.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.vgmsorganizations.domain.models.Street;
import pe.edu.vallegrande.vgmsorganizations.infrastructure.dto.ErrorMessage;
import pe.edu.vallegrande.vgmsorganizations.infrastructure.dto.ResponseDto;
import pe.edu.vallegrande.vgmsorganizations.infrastructure.repository.StreetRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/streets")
@RequiredArgsConstructor
public class StreetRest {

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

    // 🔹 Nuevo Endpoint: Devuelve todas las calles en formato ResponseDto
    @GetMapping("/list")
    public Mono<ResponseDto<List<Street>>> findAllWithResponseDto() {
        return streetRepository.findAll()
                .collectList()
                .map(streets -> new ResponseDto<>(true, streets))
                .onErrorResume(e -> Mono.just(
                        new ResponseDto<>(false,
                                new ErrorMessage(500, "Error al obtener las calles", e.getMessage()))
                ));
    }
}
