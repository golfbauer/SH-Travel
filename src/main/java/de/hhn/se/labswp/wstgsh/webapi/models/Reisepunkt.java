package de.hhn.se.labswp.wstgsh.webapi.models;

import javax.persistence.*;

@Entity(name = "reisepunkte")
@Table(name = "reisepunkte")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(
        name = "Discriminator",
        discriminatorType = DiscriminatorType.STRING,
        columnDefinition = "varchar(255)"
)
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
    private Double laengengrad;

    @Column(name = "Breitengrad", nullable = false, unique = true)
    private Double breitengrad;

    @Column(name = "NutzerEMail", nullable = false)
    private String nutzerEmail;

    @Column(name = "Name", nullable = false)
    private String name;

    public Reisepunkt(Double laengengrad, Double breitengrad, String nutzerEmail, String name) {
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

    //
    // ## ENTFERNEN ##
    // ## id ist updatable false ##
    //
    // ## just for test cases
    //
    public void setId(Long id) {
        this.id = id;
    }

    public Double getLaengengrad() {
        return laengengrad;
    }

    public void setLaengengrad(Double laengengrad) {
        this.laengengrad = laengengrad;
    }

    public Double getBreitengrad() {
        return breitengrad;
    }

    public void setBreitengrad(Double breitengrad) {
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
