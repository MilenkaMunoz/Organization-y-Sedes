package pe.edu.vallegrande.structure_microservice.infrastructure.exception;

import pe.edu.vallegrande.structure_microservice.infrastructure.dto.ErrorMessage;

public class CustomException extends RuntimeException {

    private final ErrorMessage errorMessage;

    // Constructor que recibe directamente el ErrorMessage (mantener para compatibilidad)
    public CustomException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    // Nuevo constructor que recibe los tres parámetros para crear el ErrorMessage internamente
    public CustomException(int errorCode, String error, String message) {
        super(message);
        this.errorMessage = new ErrorMessage(errorCode, error, message);
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
