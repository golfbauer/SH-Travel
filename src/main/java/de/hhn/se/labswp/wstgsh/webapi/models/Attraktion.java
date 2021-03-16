package de.hhn.se.labswp.wstgsh.webapi.models;

import javax.persistence.*;

@Entity(name = "attraktion")
@Table(name = "attraktion")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorValue("Attraktion")
@PrimaryKeyJoinColumn(name="ReisepunkteID", referencedColumnName="ID")
public class Attraktion extends Reisepunkt{

    @Column(name = "Beschreibung", nullable = true)
    private String beschreibung;

    @Column(name = "Preis", nullable = true)
    private Float preis;

    public Attraktion(Long id, Float laengengrad, Float breitengrad, String nutzerEmail, String name, String beschreibung, Float preis) {
        super(id, laengengrad, breitengrad, nutzerEmail, name);
        this.beschreibung = beschreibung;
        this.preis = preis;
    }

    public Attraktion() {

    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Float getPreis() {
        return preis;
    }

    public void setPreis(Float preis) {
        this.preis = preis;
    }
}
