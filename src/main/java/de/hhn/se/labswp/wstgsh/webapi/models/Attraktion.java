package de.hhn.se.labswp.wstgsh.webapi.models;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;

import javax.persistence.*;
import java.util.ArrayList;

@Entity(name = "attraktion")
@Table(name = "attraktion")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("Attraktion")
@PrimaryKeyJoinColumn(name = "ReisepunkteID", referencedColumnName = "ID")
public class Attraktion extends Reisepunkt {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);

  @Column(name = "Beschreibung")
  private String beschreibung;

  @OneToMany(mappedBy = "attraktion")
  private ArrayList<AttraktionOeffnungszeit> attraktionOeffnungszeiten;

  /**
   * Constructor to create an object, which can be implemented into the database if needed.
   *
   * @param id           Inherited by Reisepunkt.
   * @param laengengrad  Marks exact locaion of Attraktion, North to South.
   * @param breitengrad  Marks exact locaion of Attraktion, West to East.
   * @param nutzerEmail  Email of the creator account.
   * @param name         Name of the created Attraktion.
   * @param beschreibung Short description, which gives a explenation of the Attraktion.
   */
  public Attraktion(Long id, Float laengengrad, Float breitengrad, String nutzerEmail, String name,
                    String beschreibung, ArrayList<AttraktionOeffnungszeit> attraktionOeffnungszeiten) {
    super(id, laengengrad, breitengrad, nutzerEmail, name);
    this.beschreibung = beschreibung;
    this.attraktionOeffnungszeiten = attraktionOeffnungszeiten;
  }

  public Attraktion() {

  }

  public String getBeschreibung() {
    return beschreibung;
  }

  public ArrayList<AttraktionOeffnungszeit> getAttraktionOeffnungszeiten() {
    return attraktionOeffnungszeiten;
  }

  public void setBeschreibung(String beschreibung) {
    this.beschreibung = beschreibung;
  }

  public void setAttraktionOeffnungszeiten(ArrayList<AttraktionOeffnungszeit> attraktionOeffnungszeiten) {
    this.attraktionOeffnungszeiten = attraktionOeffnungszeiten;
  }
}
