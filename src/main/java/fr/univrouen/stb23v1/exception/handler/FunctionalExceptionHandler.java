package fr.univrouen.stb23v1.exception.handler;

import fr.univrouen.stb23v1.dto.DTO;
import fr.univrouen.stb23v1.dto.error.SimpleErrorDTO;
import fr.univrouen.stb23v1.exception.FunctionalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This exception handler catches the exception thrown by the application and send an appropriate response to the user.
 */
@ControllerAdvice
public class FunctionalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Exception handler called when a FunctionalException is thrown.
     *
     * @param  err The FunctionalException thrown.
     * @param  req The user request.
     * @return     The FunctionalException error content.
     */
    @ExceptionHandler(value = { FunctionalException.class })
    public ResponseEntity<DTO> functionalError(FunctionalException err, WebRequest req) {
        return ResponseEntity
            .status(HttpStatus.valueOf(err.getStatusCode()))
            .contentType(MediaType.APPLICATION_XML)
            .body(err.getErrorContent());
    }

}