package fr.univrouen.stb23v1.constant;

import fr.univrouen.stb23v1.dto.DTO;
import fr.univrouen.stb23v1.exception.FunctionalException;

/**
 * Contains all the application errors.
 */
public enum STB23Error {

    CANT_GET_HOME_PAGE("Can't get the home page."),
    CANT_GET_HELP_PAGE("Can't get the help page."),
    STB_NOT_FOUND("Can't get the help page.", 404),
    CANT_CONVERT_STB_TO_HTML("Can't convert the STB to an HTML view."),
    CANT_VALIDATE_STB("Can't validate the STB (Internal server error)."),
    STB_IS_NOT_VALID("The given STB is not valid.", 400);

    /**
     * The error message.
     */
    private String errorMessage;

    /**
     * The HTTP status associated to the error.
     */
    private Integer httpStatus;

    /**
     * @param errorMessage The error message.
     * @param httpStatus   The HTTP status associated to the error.
     */
    STB23Error(String errorMessage, Integer httpStatus) {
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    /**
     * @param errorMessage The error message.
     */
    STB23Error(String errorMessage) {
        this(errorMessage, 500);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

}
