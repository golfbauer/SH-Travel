package de.hhn.se.labswp.wstgsh.webapi.models;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

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

  @Column(name = "Preis")
  private Float preis;

  /**
   * Constructor to create an object, which can be implemented into the database if needed.
   * @param id Inherited by Reisepunkt.
   * @param laengengrad Marks exact locaion of Attraktion, North to South.
   * @param breitengrad Marks exact locaion of Attraktion, West to East.
   * @param nutzerEmail Email of the creator account.
   * @param name Name of the created Attraktion.
   * @param beschreibung Short description, which gives a explenation of the Attraktion.
   * @param preis Preis says how much the Attraktion will cost.
   */
  public Attraktion(Long id, Float laengengrad, Float breitengrad, String nutzerEmail, String name,
                    String beschreibung, Float preis) {
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
