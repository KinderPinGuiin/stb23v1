package fr.univrouen.stb23v1.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;

/**
 * An STB feature.
 */
@Entity
@Table(name = "feature")
@XmlAccessorType(XmlAccessType.FIELD)
public class Feature {

    /**
     * Autogenerated id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Integer id;

    /**
     * The feature name.
     */
    @XmlAttribute
    private String name;

    /**
     * The feature section.
     */
    @XmlAttribute
    private Integer section;

    /**
     * The feature number.
     */
    @XmlAttribute
    private Integer number;

    /**
     * The feature description.
     */
    @XmlElement
    private String description;

    /**
     * The feature priority.
     */
    @XmlElement
    private Integer priority;

    /**
     * The feature delivery date.
     */
    @XmlElement(name = "delivery")
    private String deliveryDate;

    /**
     * The feature comment.
     */
    @XmlElement
    private String comment;

    public Feature() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getSection() {
        return section;
    }

    public Integer getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}