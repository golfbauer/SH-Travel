package de.hhn.se.labswp.wstgsh.webapi.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Reisekatalog Class, Belongs to one Nutzer and has a List of Reisen.
 */
@Entity
@Table
public class Reisekatalog {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String eMail;
  @ManyToMany(mappedBy = "reisekatalog")
  List<Reise> reise = new ArrayList<>();

  /**
   * Constructor for Reisekatalog with an already existing list of Reisen.
   *
   * @param id    id dieses Reisekatalog.
   * @param eMail String der NutzerEmail.
   * @param reise Liste mit Reisen in ihr.
   */
  public Reisekatalog(Long id, String eMail, List<Reise> reise) {
    this.id = id;
    this.eMail = eMail;
    this.reise = reise;
  }

  /**
   * Constructor for Reisekatalog without an existing list of Reisen.
   *
   * @param id    id des Reisekatalogs.
   * @param eMail String der NutzerEmail.
   */
  public Reisekatalog(Long id, String eMail) {
    this.id = id;
    this.eMail = eMail;
  }

  /**
   * Leerer Constructor for Test purposes.
   */
  public Reisekatalog() {
  }

  public String geteMail() {
    return this.eMail;
  }

  public Long getId() {
    return this.id;
  }

  public void seteMail(String eMail) {
    this.eMail = eMail;
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
}
