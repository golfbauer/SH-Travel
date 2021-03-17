package de.hhn.se.labswp.wstgsh.webapi.models;

import javax.persistence.*;

@Entity(name = "reisepunkte")
@Table(name = "reisepunkte")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(
        name = "Discriminator",
        discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue("Reisepunkte")
public class Reisepunkt {

    @Id
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "Laengengrad", nullable = false, unique = true)
    private Float laengengrad;

    @Column(name = "Breitengrad", nullable = false, unique = true)
    private Float breitengrad;

    @Column(name = "NutzerEMail", nullable = false)
    private String nutzerEmail;

    @Column(name = "Name", nullable = false)
    private String name;

    public Reisepunkt(Float laengengrad, Float breitengrad, String nutzerEmail, String name) {
        this.laengengrad = laengengrad;
        this.breitengrad = breitengrad;
        this.nutzerEmail = nutzerEmail;
        this.name = name;
    }

    public Reisepunkt() {
    }

    public Reisepunkt(Long id, Float laengengrad, Float breitengrad, String nutzerEmail, String name) {
        this.id = id;
        this.laengengrad = laengengrad;
        this.breitengrad = breitengrad;
        this.nutzerEmail = nutzerEmail;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    //
    // ## ENTFERNEN ##
    // ## id ist updatable false ##
    //
    // ## just for test cases
    //
    public void setId(Long id) {
        this.id = id;
    }

    public Float getLaengengrad() {
        return laengengrad;
    }

    public void setLaengengrad(Float laengengrad) {
        this.laengengrad = laengengrad;
    }

    public Float getBreitengrad() {
        return breitengrad;
    }

    public void setBreitengrad(Float breitengrad) {
        this.breitengrad = breitengrad;
    }

    public String getNutzerEmail() {
        return nutzerEmail;
    }

    public void setNutzerEmail(String nutzerEmail) {
        this.nutzerEmail = nutzerEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Reisepunkt{" +
                "id=" + id +
                ", laengengrad=" + laengengrad +
                ", breitengrad=" + breitengrad +
                ", nutzerEmail='" + nutzerEmail + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}