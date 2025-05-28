package pe.edu.vallegrande.structure_microservice.infrastructure.advice;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
