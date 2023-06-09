package fr.univrouen.stb23v1.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * An STB team member.
 */
@Entity
@Table(name = "member")
@XmlAccessorType(XmlAccessType.FIELD)
public class Member {

    /**
     * Autogenerated id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Integer id;

    /**
     * The member Person type.
     */
    @XmlElement
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "personId")
    private Person person;

    /**
     * The member email address.
     */
    @XmlElement
    private String mail;

    /**
     * The functions of the team member.
     */
    @XmlElement(name = "function")
    private List<String> functions = new ArrayList<>();

    public Member() {
    }

    public Integer getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public String getMail() {
        return mail;
    }

    public List<String> getFunctions() {
        return functions;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setFunctions(List<String> functions) {
        this.functions = functions;
    }

}
