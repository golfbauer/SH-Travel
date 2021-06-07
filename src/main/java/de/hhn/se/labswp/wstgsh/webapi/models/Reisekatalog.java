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
 * Reisekatalog Klasse, in dieser werden Reisen gesammelt, gehört einem Nutzer.
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
   * Constructor für Reisekatalog mit einer bereits existierenden Liste von Reisen.
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
   * Constructor für Reisekatalog ohne Liste mit Reisen.
   *
   * @param id    id des Reisekatalogs.
   * @param eMail String der NutzerEmail.
   */
  public Reisekatalog(Long id, String eMail) {
    this.id = id;
    this.eMail = eMail;
  }

  /**
   * Leerer Constructor für Testzwecke.
   */
  public Reisekatalog() {
  }

  public String geteMail() {
    return this.eMail;
  }

  public Long getId() {
    return this.getId();
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
   * Fügt eine Reise zu diesem Reisekatalog hinzu.
   *
   * @param reise Reise die du hinzufügen möchtest.
   */
  public void addReise(Reise reise) {
    this.reise.add(reise);
  }

  /**
   * Entfernt eine Reise aus diesem Reisekatalog.
   *
   * @param reise Reise die du entfernen möchtest.
   */
  public void removeReise(Reise reise) {
    this.reise.remove(reise);
  }
}
