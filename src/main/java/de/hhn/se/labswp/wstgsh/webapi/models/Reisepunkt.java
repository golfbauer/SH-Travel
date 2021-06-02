package de.hhn.se.labswp.wstgsh.webapi.models;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(
        discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue("Reisepunkt")
public class Reisepunkt {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Float laengengrad;

  private Float breitengrad;

  private String nutzerEmail;

  private String name;

  @ManyToMany(mappedBy = "reisepunkte")
  List<Reise> reisen = new ArrayList<>();

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
                    String name, List<Reise> reisen) {
    this.id = id;
    this.laengengrad = laengengrad;
    this.breitengrad = breitengrad;
    this.nutzerEmail = nutzerEmail;
    this.name = name;
    this.reisen = reisen;
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
