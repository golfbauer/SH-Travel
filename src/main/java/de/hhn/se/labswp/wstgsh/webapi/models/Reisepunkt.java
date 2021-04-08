package de.hhn.se.labswp.wstgsh.webapi.models;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity(name = "reisepunkt")
@Table(name = "reisepunkt")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(
        name = "Discriminator",
        discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue("Reisepunkt")
public class Reisepunkt {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);

  @Id
  @Column(name = "ID", updatable = false)
  private Long id;

  @Column(name = "Laengengrad", nullable = false, unique = true)
  private Float laengengrad;

  @Column(name = "Breitengrad", nullable = false, unique = true)
  private Float breitengrad;

  @Column(name = "NutzerEMail", nullable = false)
  private String nutzerEmail;

  @Column(name = "Name", nullable = false)
  private String name;

  /**
   * Constructor to be used in Reiseüunkt Controller if needed.
   * @param laengengrad Marks exact locaion of Attraktion, North to South.
   * @param breitengrad Marks exact locaion of Attraktion, West to East.
   * @param nutzerEmail Email of the creator account.
   * @param name Name of the created Punkt.
   */
  public Reisepunkt(Float laengengrad, Float breitengrad, String nutzerEmail, String name) {
    this.laengengrad = laengengrad;
    this.breitengrad = breitengrad;
    this.nutzerEmail = nutzerEmail;
    this.name = name;
  }

  public Reisepunkt() {
  }

  /**
   * Constructor to create an object, which can be implemented into the database if needed.
   * @param id Id to be created for unique Primary Key.
   * @param laengengrad Marks exact locaion of Attraktion, North to South.
   * @param breitengrad Marks exact locaion of Attraktion, West to East.
   * @param nutzerEmail Email of the creator account.
   * @param name Name of the created Punkt.
   */
  public Reisepunkt(Long id, Float laengengrad, Float breitengrad, String nutzerEmail,
                    String name) {
    this.id = id;
    this.laengengrad = laengengrad;
    this.breitengrad = breitengrad;
    this.nutzerEmail = nutzerEmail;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  //
  // ## ENTFERNEN ##
  // ## id ist updatable false ##
  //
  // ## just for test cases
  //
  public void setId(Long id) {
    this.id = id;
  }

  public Float getLaengengrad() {
    return laengengrad;
  }

  public void setLaengengrad(Float laengengrad) {
    this.laengengrad = laengengrad;
  }

  public Float getBreitengrad() {
    return breitengrad;
  }

  public void setBreitengrad(Float breitengrad) {
    this.breitengrad = breitengrad;
  }

  public String getNutzerEmail() {
    return nutzerEmail;
  }

  public void setNutzerEmail(String nutzerEmail) {
    this.nutzerEmail = nutzerEmail;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Reisepunkt{"
            + "id=" + id
            + ", laengengrad=" + laengengrad
            + ", breitengrad=" + breitengrad
            + ", nutzerEmail='" + nutzerEmail + '\''
            + ", name='" + name + '\''
            + '}';
  }
}
