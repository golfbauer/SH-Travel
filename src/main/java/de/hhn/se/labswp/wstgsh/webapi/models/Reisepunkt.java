package de.hhn.se.labswp.wstgsh.webapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;
import de.hhn.se.labswp.wstgsh.webapi.models.nutzer.Nutzer;

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

  private String name;

  private boolean oeffentlich;

  @ManyToMany(mappedBy = "reisepunkte")
  @JsonIgnoreProperties("reisepunkte")
  private List<Reise> reisen = new ArrayList<>();

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(
          name = "nutzer_id",
          nullable = false
  )
  @JsonIgnore
  private Nutzer nutzer;

  /**
   * Constructor to create an object, which can be implemented into the database if needed.
   * @param id Id to be created for unique Primary Key.
   * @param laengengrad Marks exact locaion of Attraktion, North to South.
   * @param breitengrad Marks exact locaion of Attraktion, West to East.
   * @param name Name of the created Punkt.
   * @param oeffentlich Sets privaty setting of Reise.
   * @param reisen Contains all Reisen, which Reisepunkt is part of.
   * @param nutzer Owner of the Reisepunkt.
   */
  public Reisepunkt(Long id, Float laengengrad, Float breitengrad,
                    String name, boolean oeffentlich, List<Reise> reisen, Nutzer nutzer) {
    this.id = id;
    this.laengengrad = laengengrad;
    this.breitengrad = breitengrad;
    this.name = name;
    this.oeffentlich = oeffentlich;
    this.reisen = reisen;
    this.nutzer = nutzer;
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
  public Reisepunkt(Float laengengrad, Float breitengrad,
                    String name, boolean oeffentlich, List<Reise> reisen, Nutzer nutzer) {
    this.laengengrad = laengengrad;
    this.breitengrad = breitengrad;
    this.name = name;
    this.oeffentlich = oeffentlich;
    this.reisen = reisen;
    this.nutzer = nutzer;
  }

  /**
   * Constructor to be used in Reisepunkt Controller if needed.
   * @param laengengrad Marks exact locaion of Attraktion, North to South.
   * @param breitengrad Marks exact locaion of Attraktion, West to East.
   * @param name Name of the created Punkt.
   */
  public Reisepunkt(Float laengengrad, Float breitengrad, String name) {
    this.laengengrad = laengengrad;
    this.breitengrad = breitengrad;
    this.name = name;
  }

  public Reisepunkt() {
  }

  public Long getId() {
    return id;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Reise> getReisen() {
    return reisen;
  }

  public void setReisen(List<Reise> reisen) {
    this.reisen = reisen;
  }

  public void addReise(Reise reise) {
    reisen.add(reise);
  }

  public void removeReise(Reise reise) {
    reisen.remove(reise);
  }

  public Nutzer getNutzer() {
    return nutzer;
  }

  public void setNutzer(Nutzer nutzer) {
    this.nutzer = nutzer;
  }

  public boolean isOeffentlich() {
    return oeffentlich;
  }

  public void setOeffentlich(boolean oeffentlich) {
    this.oeffentlich = oeffentlich;
  }

  @Override
  public String toString() {
    return "Reisepunkt{" + "id="
            + id + ", laengengrad="
            + laengengrad + ", breitengrad="
            + breitengrad + ", name='"
            + name + '\'' + ", oeffentlich="
            + oeffentlich + ", reisen="
            + reisen + ", nutzer="
            + nutzer
            + '}';
  }
}
