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

  /**
   * This is a many to many relationship between Reisepunkt and Reise creating a new Table
   * containing ids of each class, called reise_reisepunkt.
   */
  @ManyToMany(cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
  })
  @JoinColumn(nullable = false)
  @JoinTable(
          name = "reise_reisepunkte",
          joinColumns = @JoinColumn(name = "reise_id"),
          inverseJoinColumns = @JoinColumn(name = "reisepunkte_id")
  )
  List<Reisepunkt> reisepunkte = new ArrayList<>();

  /**
   * This is a many to many relationship between Reisekatalog and Reise creating a new Table
   * containing ids of each class, called reise_reisekatalog.
   */
  @ManyToMany(cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
  })
  @JoinTable(
          name = "reise_reisekatalog",
          joinColumns = @JoinColumn(name = "reise_id"),
          inverseJoinColumns = @JoinColumn(name = "reisekatalog_id")
  )
  List<Reisekatalog> reisekatalog = new ArrayList<>();

  /**
   * Constructor for test purposes.
   * @param id ID of the Reise.
   * @param termin Time the trip will start at.
   * @param name Costum name of the Reise.
   * @param oeffentlich Whether the Reise can be seen by other Users.
   * @param reisepunkte List of all Reisepunkte inside the Reise.
   */
  public Reise(Long id, Date termin, String name, boolean oeffentlich,
               List<Reisepunkt> reisepunkte, List<Reisekatalog> reisekatalog) {
    this.id = id;
    this.termin = termin;
    this.name = name;
    this.oeffentlich = oeffentlich;
    this.reisepunkte = reisepunkte;
    this.reisekatalog = reisekatalog;
  }

  /**
   * Constructor for test purposes.
   * @param termin Time the trip will start at.
   * @param name Costum name of the Reise.
   * @param oeffentlich Whether the Reise can be seen by other Users.
   * @param reisepunkte List of all Reisepunkte inside the Reise.
   */
  public Reise(Date termin, String name, boolean oeffentlich, List<Reisepunkt> reisepunkte,
               List<Reisekatalog> reisekatalog) {
    this.termin = termin;
    this.name = name;
    this.oeffentlich = oeffentlich;
    this.reisepunkte = reisepunkte;
    this.reisekatalog = reisekatalog;
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

  public List<Reisekatalog> getReisekatalog() {
    return reisekatalog;
  }

  public void setReisekatalog(List<Reisekatalog> reisekatalog) {
    this.reisekatalog = reisekatalog;
  }

  public void addReisekatalog(Reisekatalog reisekatalog) {
    this.reisekatalog.add(reisekatalog);
  }

  public void removeReisekatalog(Reisekatalog reisekatalog) {
    this.reisekatalog.remove(reisekatalog);
  }

  public void addReisepunkt(Reisepunkt reisepunkt) {
    this.reisepunkte.add(reisepunkt);
  }

  public void removeReisepunkt(Reisepunkt reisepunkt) {
    this.reisepunkte.remove(reisepunkt);
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
