package fr.univrouen.stb23v1.exception;


import fr.univrouen.stb23v1.dto.DTO;

/**
 * A functional exception is an exception thrown inside the services, they contain a DTO that will automatically be sent
 * to the user thanks to the exception handler.
 */
public class FunctionalException extends Exception {

    private final DTO errorContent;

    private final Integer statusCode;

    /**
     * Create a FunctionalException with the given message, error content and status code.
     *
     * @param message      The error message.
     * @param errorContent The error content as a DTO.
     * @param statusCode   The HTTP status code to send to the user.
     */
    public FunctionalException(String message, DTO errorContent, Integer statusCode) {
        super(message);
        this.errorContent = errorContent;
        this.statusCode = statusCode;
    }

    /**
     * Create a FunctionalException with the given message, error content and a 500 HTTP status code.
     *
     * @param message      The error message.
     * @param errorContent The error content as a DTO.
     */
    public FunctionalException(String message, DTO errorContent) {
        this(message, errorContent, 500);
    }

    public DTO getErrorContent() {
        return this.errorContent;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

}
