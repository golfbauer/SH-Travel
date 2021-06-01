package de.hhn.se.labswp.wstgsh.webapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "reisekatalog")
@Table(name = "reisekatalog")
public class Reisekatalog {
  @Id
  @Column(name = "ID", updatable = false)
  private Long id;
  @Column(name = "eMail")
  private String eMail;

  public Reisekatalog(Long id, String eMail){
    this.id = id;
    this.eMail = eMail;
  }

  public String geteMail(){
    return this.eMail;
  }
  public void seteMail(String eMail){
    this.eMail = eMail;
  }
}
