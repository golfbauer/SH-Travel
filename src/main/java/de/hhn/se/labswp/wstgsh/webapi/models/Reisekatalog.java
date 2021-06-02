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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Reisekatalog {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String eMail;
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinColumn()
  List<Reise> reise = new ArrayList<>();

  /**
   * Constructor for Reisekatalog with an already existing List of Reisen in it
   * @param id id of this Reisekatalog
   * @param eMail String of the Nutzers eMail
   * @param reise a List with Reisen in it.
   */
  public Reisekatalog(Long id, String eMail, List<Reise> reise){
    this.id = id;
    this.eMail = eMail;
    this.reise = reise;
  }
  /**
   * Constructor for Reisekatalog without existing Reisen in it.
   * @param id id of this Reisekatalog
   * @param eMail String of the Nutzers eMail
   */
  public Reisekatalog(Long id, String eMail){
    this.id = id;
    this.eMail = eMail;
  }

  /**
   * Empty Constructor for Purposes.
   */
  public Reisekatalog() {
  }

  public String geteMail(){
    return this.eMail;
  }
  public void seteMail(String eMail){
    this.eMail = eMail;
  }
  public List<Reise> getReise(){
    return this.reise;
  }
  public void setReise(List<Reise> reise) {
    this.reise = reise;
  }

  /**
   * Adds a Reise to this Reisekatalog
   * @param reise Reise you want to add.
   */
  public void addReise(Reise reise){
    this.reise.add(reise);
  }

  /**
   * Removes a Reise from this Reisekatalog
   * @param reise Reise you want to remove.
   */
  public void removeReise(Reise reise){
    this.reise.remove(reise);
  }
}
