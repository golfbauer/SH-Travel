package de.hhn.se.labswp.wstgsh.webapi.models;


import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

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
   * @param nutzerEmail Email of the creator account.
   * @param name Name of the created Punkt.
   */
  public Punkt(Long id, Float laengengrad, Float breitengrad, String nutzerEmail, String name) {
    super(id, laengengrad, breitengrad, nutzerEmail, name);
  }

  public Punkt(Float laengengrad, Float breitengrad, String nutzerEmail, String name) {
    super(laengengrad, breitengrad, nutzerEmail, name);
  }

  public Punkt() {

  }
}
