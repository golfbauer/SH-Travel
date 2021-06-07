package de.hhn.se.labswp.wstgsh.webapi.models;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("Attraktion")
public class Attraktion extends Reisepunkt {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);


  private String beschreibung;

  @OneToMany(mappedBy = "attraktion", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AttraktionOeffnungszeit> attraktionOeffnungszeiten = new ArrayList<>();

  /**
   * Constructor to create an object, which can be implemented into the database if needed.
   *
   * @param laengengrad  Marks exact locaion of Attraktion, North to South.
   * @param breitengrad  Marks exact locaion of Attraktion, West to East.
   * @param nutzerEmail  Email of the creator account.
   * @param name         Name of the created Attraktion.
   * @param beschreibung Short description, which gives a explenation of the Attraktion.
   */
  public Attraktion(Float laengengrad, Float breitengrad, String nutzerEmail, String name,
                    String beschreibung, List<AttraktionOeffnungszeit> list) {
    super(laengengrad, breitengrad, nutzerEmail, name);
    this.beschreibung = beschreibung;
    this.attraktionOeffnungszeiten = list;
  }

  public Attraktion(Long id, Float laengengrad, Float breitengrad, String nutzerEmail, String name,
                    String beschreibung, List<AttraktionOeffnungszeit> list) {
    super(id, laengengrad, breitengrad, nutzerEmail, name);
    this.beschreibung = beschreibung;
    this.attraktionOeffnungszeiten = list;
  }

  public Attraktion() {

  }

  public String getBeschreibung() {
    return beschreibung;
  }

  public List<AttraktionOeffnungszeit> getAttraktionOeffnungszeiten() {
    return attraktionOeffnungszeiten;
  }

  public void setBeschreibung(String beschreibung) {
    this.beschreibung = beschreibung;
  }

  public void SetAttraktionOeffnungszeiten(
          List<AttraktionOeffnungszeit> attraktionOeffnungszeiten) {
    this.attraktionOeffnungszeiten = attraktionOeffnungszeiten;
  }
}
