package de.hhn.se.labswp.wstgsh.webapi.models;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;

import javax.persistence.*;


@Entity(name = "attraktion_oeffungszeit")
@Table(name = "attraktion_oeffungszeit")
@PrimaryKeyJoinColumn(name = "AttraktionIndex", referencedColumnName = "ReisepunkteID")

public class AttraktionOeffnungszeit {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);
    @Id
    @Column(name = "ID", updatable = false)
    private int id;

    @Column(name = "Oeffnungszeit")
    private String oeffnungszeit;

    @Column(name = "AttraktionReisepunkteID")
    private int attraktionReisepunkteID;

    public AttraktionOeffnungszeit(int id, String oeffnungszeit, int attraktionReisepunkteID) {
        this.id = id;
        this.oeffnungszeit = oeffnungszeit;
        this.attraktionReisepunkteID = attraktionReisepunkteID;
    }

    public AttraktionOeffnungszeit(String oeffnungszeit, int attraktionReisepunkteID) {
        this.oeffnungszeit = oeffnungszeit;
        this.attraktionReisepunkteID = attraktionReisepunkteID;
    }

    public AttraktionOeffnungszeit() {

    }

    public void setOeffnungszeit(String oeffnungszeit) {
        this.oeffnungszeit = oeffnungszeit;
    }

    public void setAttraktionReisepunkteID(int attraktionReisepunkteID) {
        this.attraktionReisepunkteID = attraktionReisepunkteID;
    }

    public int getId() {
        return id;
    }

    public String getOeffnungszeit() {
        return oeffnungszeit;
    }

    public int getAttraktionReisepunkteID() {
        return attraktionReisepunkteID;
    }

    @Override
    public String toString() {
        return "AttraktionOeffnungszeit{"
                + "id=" + id
                + ", oeffnungszeit=" + oeffnungszeit
                + ", attraktionReisepunkteID=" + attraktionReisepunkteID
                + '}';
    }
}
