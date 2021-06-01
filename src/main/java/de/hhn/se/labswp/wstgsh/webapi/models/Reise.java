package de.hhn.se.labswp.wstgsh.webapi.models;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table
public class Reise {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Date termin;

  private String name;

  private boolean oeffentlich;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinColumn(nullable = false)
  List<Reisepunkt> reisepunkte = new ArrayList<>();

  /**
   * Constructor for test purposes.
   * @param id ID of the Reise.
   * @param termin Time the trip will start at.
   * @param name Costum name of the Reise.
   * @param oeffentlich Whether the Reise can be seen by other Users.
   * @param reisepunkte List of all Reisepunkte inside the Reise.
   */
  public Reise(Long id, Date termin, String name, boolean oeffentlich,
               List<Reisepunkt> reisepunkte) {
    this.id = id;
    this.termin = termin;
    this.name = name;
    this.oeffentlich = oeffentlich;
    this.reisepunkte = reisepunkte;
  }

  /**
   * Constructor for test purposes.
   * @param termin Time the trip will start at.
   * @param name Costum name of the Reise.
   * @param oeffentlich Whether the Reise can be seen by other Users.
   * @param reisepunkte List of all Reisepunkte inside the Reise.
   */
  public Reise(Date termin, String name, boolean oeffentlich, List<Reisepunkt> reisepunkte) {
    this.termin = termin;
    this.name = name;
    this.oeffentlich = oeffentlich;
    this.reisepunkte = reisepunkte;
  }

  public Reise() {

  }

  public Long getId() {
    return id;
  }

  public Date getTermin() {
    return termin;
  }

  public void setTermin(Date termin) {
    this.termin = termin;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isOeffentlich() {
    return oeffentlich;
  }

  public void setOeffentlich(boolean oeffentlich) {
    this.oeffentlich = oeffentlich;
  }

  public List<Reisepunkt> getReisepunkte() {
    return reisepunkte;
  }

  public void setReisepunkte(List<Reisepunkt> reisepunkte) {
    this.reisepunkte = reisepunkte;
  }

  @Override
  public String toString() {
    return "Reise{"
            + "id=" + id
            + ", termin=" + termin
            + ", name='" + name + '\''
            + ", oeffentlich=" + oeffentlich
            + ", reisepunkte=" + reisepunkte
            + '}';
  }
}
