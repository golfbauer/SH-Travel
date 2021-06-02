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

  public Reisekatalog(Long id, String eMail, List<Reise> reise){
    this.id = id;
    this.eMail = eMail;
    this.reise = reise;
  }
  public Reisekatalog(Long id, String eMail){
    this.id = id;
    this.eMail = eMail;
  }

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

  public void addReise(Reise reise){
    this.reise.add(reise);
  }
  public void removeReise(Reise reise){
    this.reise.remove(reise);
  }
}
