package de.hhn.se.labswp.wstgsh.api.models;
import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.util.List;

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
   * @param name Name of the created Sehenswuerdigkeit.
   * @param beschreibung Short description, which gives a explenation of the Sehenswuerdigkeit.
   */
  public Sehenswuerdigkeit(Long id, Float laengengrad, Float breitengrad,
                           String name, String beschreibung, boolean oeffentlich,
                           List<Reise> reisen, Nutzer nutzer) {
    super(id, laengengrad, breitengrad, name, oeffentlich, reisen, nutzer);
    this.beschreibung = beschreibung;
  }

  /**
   * Constructor to create an object, which can be implemented into the database if needed.
   * @param laengengrad Marks exact locaion of Sehenswuerdigkeit, North to South.
   * @param breitengrad Marks exact locaion of Sehenswuerdigkeit, West to East.
   * @param name Name of the created Sehenswuerdigkeit.
   * @param beschreibung Short description, which gives a explenation of the Sehenswuerdigkeit.
   */
  public Sehenswuerdigkeit(Float laengengrad, Float breitengrad,
                           String name, String beschreibung, boolean oeffentlich) {
    super(laengengrad, breitengrad, name, oeffentlich);
    this.beschreibung = beschreibung;
  }

  /**
   * Constructor to create an object, which can be implemented into the database if needed.
   * @param laengengrad Marks exact locaion of Attraktion, North to South.
   * @param breitengrad Marks exact locaion of Attraktion, West to East.
   * @param name Name of the created Punkt.
   * @param oeffentlich Sets privaty setting of Reise.
   * @param reisen Contains all Reisen, which Reisepunkt is part of.
   * @param nutzer Owner of the Reisepunkt.
   */
  public Sehenswuerdigkeit(Float laengengrad, Float breitengrad,
                           String name, String beschreibung, boolean oeffentlich,
                           List<Reise> reisen, Nutzer nutzer) {
    super(laengengrad, breitengrad, name, oeffentlich, reisen, nutzer);
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
