package de.hhn.se.labswp.wstgsh.webapi;

import javax.persistence.*;

@Entity(name = "reisepunkte")
@Table
@DiscriminatorColumn(name = "Discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Nutzer")
public class Reisepunkt {

    @Id
    @SequenceGenerator(
            name = "reisepunkt_sequence",
            sequenceName = "reisepunkt_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reisepunkt_sequence"
    )
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "Laengengrad", nullable = false, unique = true)
    private Long laengengrad;

    @Column(name = "Breitengrad", nullable = false, unique = true)
    private Long breitengrad;

    @Column(name = "NutzerEmail", nullable = false)
    private String nutzerEmail;

    @Column(name = "Name", nullable = false)
    private String name;

    public Reisepunkt(Long laengengrad, Long breitengrad, String nutzerEmail, String name) {
        this.laengengrad = laengengrad;
        this.breitengrad = breitengrad;
        this.nutzerEmail = nutzerEmail;
        this.name = name;
    }

    public Reisepunkt() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLaengengrad() {
        return laengengrad;
    }

    public void setLaengengrad(Long laengengrad) {
        this.laengengrad = laengengrad;
    }

    public Long getBreitengrad() {
        return breitengrad;
    }

    public void setBreitengrad(Long breitengrad) {
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
