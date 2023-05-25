package fr.univrouen.stb23v1.dto;

import fr.univrouen.stb23v1.constant.STBStatus;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * This DTO represents an STB status.
 */
@XmlRootElement(name = "stb-status")
@XmlAccessorType(XmlAccessType.FIELD)
public class STBStatusDTO implements DTO {

    /**
     * The ID of the STB.
     */
    @XmlElement(name = "id")
    private Integer id;

    /**
     * The STB status.
     */
    @XmlElement(name = "status")
    private STBStatus status;

    /**
     * @param id     The ID of the STB.
     * @param status The STB status.
     */
    public STBStatusDTO(Integer id, STBStatus status) {
        this.id = id;
        this.status = status;
    }

    public STBStatusDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public STBStatus getStatus() {
        return status;
    }

    public void setStatus(STBStatus status) {
        this.status = status;
    }

}
