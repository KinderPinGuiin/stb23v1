package fr.univrouen.stb23v1.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.ArrayList;
import java.util.List;

/**
 * An STB team.
 */
@Entity
@Table(name = "team")
@XmlAccessorType(XmlAccessType.FIELD)
public class Team {

    /**
     * Autogenerated id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Integer id;

    /**
     * The team members.
     */
    @OneToMany(targetEntity=Member.class, cascade=CascadeType.ALL)
    @XmlElement(name = "member")
    private List<Member> members = new ArrayList<>();

    public Team() {
    }

    public Integer getId() {
        return id;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

}
