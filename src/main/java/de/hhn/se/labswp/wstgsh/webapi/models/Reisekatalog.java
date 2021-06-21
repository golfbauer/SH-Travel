package de.hhn.se.labswp.wstgsh.webapi.models;

import de.hhn.se.labswp.wstgsh.webapi.models.nutzer.Nutzer;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Reisekatalog Class, Belongs to one Nutzer and has a List of Reisen.
 */
@Entity
@Table
public class Reisekatalog {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToMany(mappedBy = "reisekatalog")
  private List<Reise> reise = new ArrayList<>();

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "nutzer_id", referencedColumnName = "id")
  private Nutzer nutzer;


  /**
   * Constructor for Reisekatalog with an already existing list of Reisen.
   * @param id    id dieses Reisekatalog.
   * @param reise Liste mit Reisen in ihr.
   *  @param nutzer Owner of the Reisekatalog.
   */
  public Reisekatalog(Long id, List<Reise> reise, Nutzer nutzer) {
    this.id = id;
    this.reise = reise;
    this.nutzer = nutzer;
  }

  /**
   * Constructor for Reisekatalog with an already existing list of Reisen.
   * @param reisen Liste mit Reisen in ihr.
   *  @param nutzer Owner of the Reisekatalog.
   */
  public Reisekatalog(List<Reise> reisen, Nutzer nutzer) {
    this.reise = reisen;
    this.nutzer = nutzer;
  }

  /**
   * Leerer Constructor for Test purposes.
   */
  public Reisekatalog() {
  }

  public Long getId() {
    return this.id;
  }

  public List<Reise> getReise() {
    return this.reise;
  }

  public void setReise(List<Reise> reise) {
    this.reise = reise;
  }

  /**
   * Adds a Reise to this Reisekatalog.
   *
   * @param reise Reise die du hinzufügen möchtest.
   */
  public void addReise(Reise reise) {
    this.reise.add(reise);
  }

  /**
   * Removes a Reise from this Reisekatalog.
   *
   * @param reise Reise die du entfernen möchtest.
   */
  public void removeReise(Reise reise) {
    this.reise.remove(reise);
  }

  public Nutzer getNutzer() {
    return nutzer;
  }

  public void setNutzer(Nutzer nutzer) {
    this.nutzer = nutzer;
  }
}
