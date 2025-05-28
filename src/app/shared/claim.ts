import { user } from "./user";

/**
 * Interfaz que representa un reclamo en el sistema
 */
export interface claim {
    /** Identificador único del reclamo */
    claimId: string;
    
    /** Identificador del usuario que realizó el reclamo */
    userId: string;
    
    /** Identificador del pago asociado al reclamo */
    paymentId: string;
    
    /** Tipo de reclamo (ej: 'PAYMENT', 'SERVICE', 'PRODUCT') */
    claim_type: 'PAYMENT' | 'SERVICE' | 'PRODUCT';
    
    /** Descripción detallada del reclamo */
    description: string;
    
    /** Fecha en que se realizó el reclamo (formato ISO) */
    claim_date: string;
    
    /** Estado actual del reclamo (ej: 'PENDING', 'IN_PROGRESS', 'RESOLVED', 'CLOSED') */
    status: 'PENDING' | 'IN_PROGRESS' | 'RESOLVED' | 'CLOSED';
    
    /** Fecha de resolución del reclamo (formato ISO) */
    resolution_date: string;
    
    /** Respuesta o solución proporcionada al reclamo */
    response: string;
    
    /** Identificador del usuario que está atendiendo el reclamo */
    attending_user_id: string;
    
    /** Fecha de registro del reclamo en el sistema (formato ISO) */
    registration_date: string;
    
    /** Información del usuario asociado al reclamo */
    dataUser: user;
}