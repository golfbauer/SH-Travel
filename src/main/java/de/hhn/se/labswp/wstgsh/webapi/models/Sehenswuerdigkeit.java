package de.hhn.se.labswp.wstgsh.webapi.models;
import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("Sehenswuerdigkeit")
public class Sehenswuerdigkeit extends Reisepunkt {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);


  private String beschreibung;

  /**
   * Constructor to create an object, which can be implemented into the database if needed.
   * @param id Inherited by Reisepunkt.
   * @param laengengrad Marks exact locaion of Sehenswuerdigkeit, North to South.
   * @param breitengrad Marks exact locaion of Sehenswuerdigkeit, West to East.
   * @param nutzerEmail Email of the creator account.
   * @param name Name of the created Sehenswuerdigkeit.
   * @param beschreibung Short description, which gives a explenation of the Sehenswuerdigkeit.
   */
  public Sehenswuerdigkeit(Long id, Float laengengrad, Float breitengrad, String nutzerEmail,
                           String name, String beschreibung) {
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
