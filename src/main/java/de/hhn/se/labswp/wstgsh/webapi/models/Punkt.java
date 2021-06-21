package de.hhn.se.labswp.wstgsh.webapi.models;


import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;
import de.hhn.se.labswp.wstgsh.webapi.models.nutzer.Nutzer;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("Punkt")
public class Punkt extends Reisepunkt {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);

  /**
   * Constructor to create an object, which can be implemented into the database if needed.
   * @param id Inherited by Reisepunkt.
   * @param laengengrad Marks exact locaion of Punkt, North to South.
   * @param breitengrad Marks exact locaion of Punkt, West to East.
   * @param name Name of the created Punkt.
   */
  public Punkt(Long id, Float laengengrad, Float breitengrad, String name, boolean oeffentlich,
               List<Reise> reisen, Nutzer nutzer) {
    super(id, laengengrad, breitengrad, name, oeffentlich, reisen, nutzer);
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
  public Punkt(Float laengengrad, Float breitengrad, String nutzerEmail, String name,
          boolean oeffentlich, List<Reise> reisen, Nutzer nutzer) {
    super(laengengrad, breitengrad, name, oeffentlich, reisen, nutzer);
  }

  public Punkt() {

  }
}
