package fr.univrouen.stb23v1.dto.error;

import fr.univrouen.stb23v1.constant.STBErrorDetail;
import fr.univrouen.stb23v1.constant.STBStatus;
import fr.univrouen.stb23v1.dto.DTO;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * A DTO modeling an STB error.
 */
@XmlRootElement(name = "stb-error")
@XmlAccessorType(XmlAccessType.FIELD)
public class STBError implements DTO {

    /**
     * The error status.
     */
    @XmlElement(name = "status")
    private STBStatus status;

    /**
     * The error detail.
     */
    @XmlElement(name = "detail")
    private STBErrorDetail detail;

    /**
     * @param status The error status.
     * @param detail The error detail.
     */
    public STBError(STBStatus status, STBErrorDetail detail) {
        super();
        this.status = status;
        this.detail = detail;
    }

    public STBError() {
    }

    public STBStatus getStatus() {
        return status;
    }

    public void setStatus(STBStatus status) {
        this.status = status;
    }

    public STBErrorDetail getDetail() {
        return detail;
    }

    public void setDetail(STBErrorDetail detail) {
        this.detail = detail;
    }

}
