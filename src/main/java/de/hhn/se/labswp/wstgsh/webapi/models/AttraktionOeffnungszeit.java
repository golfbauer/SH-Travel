package de.hhn.se.labswp.wstgsh.webapi.models;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;

import javax.persistence.*;


@Entity(name = "attraktion_oeffungszeit")
@Table(name = "attraktion_oeffungszeit")

public class AttraktionOeffnungszeit {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);
  @Id
  @Column(name = "AttraktionIndex", updatable = false)
  private int id;

  @Column(name = "Oeffnungszeit")
  private String oeffnungszeit;

  /**
   * Constructor to create an object, which can be implemented into the database if needed.
   *
   * @param id                      Id to be created for unique Primary Key.
   * @param oeffnungszeit           Opening time for the Attraktion
   * @param attraktionReisepunkteID Attraktion id which this opening time is linked
   */
  public AttraktionOeffnungszeit(int id, String oeffnungszeit, int attraktionReisepunkteID) {
    this.id = id;
    this.oeffnungszeit = oeffnungszeit;
  }

  /**
   * Constructor to create an object, with an automaticly generated Primary Key.
   *
   * @param oeffnungszeit           Opening time for the Attraktion
   * @param attraktionReisepunkteID Attraktion id which this opening time is linked
   */
  public AttraktionOeffnungszeit(String oeffnungszeit, int attraktionReisepunkteID) {
    this.oeffnungszeit = oeffnungszeit;
  }

  public AttraktionOeffnungszeit() {

  }

  public void setOeffnungszeit(String oeffnungszeit) {
    this.oeffnungszeit = oeffnungszeit;
  }

  public void setAttraktionReisepunkteID(int attraktionReisepunkteID) {
  }

  public int getId() {
    return id;
  }

  public String getOeffnungszeit() {
    return oeffnungszeit;
  }

  @Override
  public String toString() {
    return "AttraktionOeffnungszeit{"
            + "id=" + id
            + ", oeffnungszeit=" + oeffnungszeit
            + '}';
  }
}
