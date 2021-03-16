package de.hhn.se.labswp.wstgsh.webapi.models;


import javax.persistence.*;

@Entity(name = "punkt")
@Table(name = "punkt")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorValue("Punkt")
@PrimaryKeyJoinColumn(name="ReisepunkteID", referencedColumnName="ID")
public class Punkt extends Reisepunkt{

    public Punkt(Long id, Float laengengrad, Float breitengrad, String nutzerEmail, String name) {
        super(id, laengengrad, breitengrad, nutzerEmail, name);
    }

    public Punkt() {

    }
}
