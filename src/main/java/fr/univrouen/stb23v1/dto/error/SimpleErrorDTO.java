package fr.univrouen.stb23v1.dto.error;

import fr.univrouen.stb23v1.dto.DTO;
import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

/**
 * A DTO modeling a simple error.
 */
@XmlRootElement(name = "stb-error")
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleErrorDTO implements DTO {

    /**
     * The error message.
     */
    @XmlElement(name = "error-message")
    private String errorMessage;

    /**
     * @param errorMessage The error message.
     */
    public SimpleErrorDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public SimpleErrorDTO() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
