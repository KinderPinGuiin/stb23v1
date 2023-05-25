package fr.univrouen.stb23v1.dto;

import jakarta.xml.bind.annotation.*;

/**
 * Summary of an STB.
 */
@XmlRootElement(name = "stb-summary")
@XmlAccessorType(XmlAccessType.FIELD)
public class STBSummaryDTO implements DTO {

    /**
     * The STB ID.
     */
    @XmlAttribute
    private Integer id;

    /**
     * The STB title.
     */
    @XmlElement
    private String title;

    /**
     * The STB description.
     */
    @XmlElement
    private String description;

    /**
     * The STB validation date.
     */
    @XmlElement(name = "validation-date")
    private String validationDate;

    /**
     * The STB client entity name.
     */
    @XmlElement(name = "client-entity")
    private String clientEntity;

    /**
     * @param id             The STB ID.
     * @param title          The STB title.
     * @param description    The STB description.
     * @param validationDate The STB validation date.
     * @param client         The STB client entity name.
     */
    public STBSummaryDTO(Integer id, String title, String description, String validationDate, String clientEntity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.validationDate = validationDate;
        this.clientEntity = clientEntity;
    }

    public STBSummaryDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(String validationDate) {
        this.validationDate = validationDate;
    }

    public String getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(String clientEntity) {
        this.clientEntity = clientEntity;
    }

}
