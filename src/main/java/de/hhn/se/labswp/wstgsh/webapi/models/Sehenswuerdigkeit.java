package de.hhn.se.labswp.wstgsh.webapi.models;

import de.hhn.se.labswp.wstgsh.webapi.models.Reisepunkt;

import javax.persistence.*;

@Entity(name = "sehenswuerdigkeit")
@Table(name = "sehenswuerdigkeit")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorValue("Sehenswuerdigkeit")
@PrimaryKeyJoinColumn(name="ReisepunkteID", referencedColumnName="ID")
public class Sehenswuerdigkeit extends Reisepunkt {

    @Column(name = "Beschreibung", nullable = true)
    private String beschreibung;

    public Sehenswuerdigkeit(Long id, Float laengengrad, Float breitengrad, String nutzerEmail, String name, String beschreibung) {
        super(id, laengengrad, breitengrad, nutzerEmail, name);
        this.beschreibung = beschreibung;
    }

    public Sehenswuerdigkeit() {

    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
