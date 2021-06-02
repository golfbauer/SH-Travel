package de.hhn.se.labswp.wstgsh.webapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Reisekatalog {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String eMail;

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
}
