package de.hhn.se.labswp.wstgsh.webapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;
import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table
public class AttraktionOeffnungszeit {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;


  private DayOfWeek tagDerWoche;

  private LocalTime oeffnetUm;

  private LocalTime schliestUm;

  private boolean ganztaegig;

  private boolean geschlossen;

  @ManyToOne
  @JoinColumn(nullable = false)
  @JsonIgnore
  private Attraktion attraktion;

  /**
   * Used to create a new Oeffnungszeit which is either all day opened or closed.
   * @param tagDerWoche Day of the week.
   * @param ganztaegig If true = opened all day, false = closed all day
   * @param attraktion Referenced Attraktion.
   */
  public AttraktionOeffnungszeit(DayOfWeek tagDerWoche, boolean ganztaegig, Attraktion attraktion) {
    this.tagDerWoche = tagDerWoche;
    this.attraktion = attraktion;
    this.oeffnetUm = null;
    this.schliestUm = null;
    if (ganztaegig = true) {
      this.ganztaegig = ganztaegig;
    } else {
      this.geschlossen = true;
    }
  }

  /**
   * Used to create a new Oeffnungszeit which is opened in a certain time of the day.
   * @param tagDerWoche Day of the Week.
   * @param oeffnetUm When to open.
   * @param schliestUm When to close.
   * @param attraktion Referenced Attraktion.
   */
  public AttraktionOeffnungszeit(DayOfWeek tagDerWoche, LocalTime oeffnetUm, LocalTime schliestUm,
                                 Attraktion attraktion) {
    this.tagDerWoche = tagDerWoche;
    this.attraktion = attraktion;
    this.oeffnetUm = oeffnetUm;
    this.schliestUm = schliestUm;
    this.geschlossen = false;
    this.ganztaegig = false;
  }

  public AttraktionOeffnungszeit() {

  }

  public void setAttraktion(Attraktion attraktion) {
    this.attraktion = attraktion;
  }

  public int getId() {
    return id;
  }

  public Attraktion getAttraktion() {
    return attraktion;
  }

  public DayOfWeek getTagDerWoche() {
    return tagDerWoche;
  }

  public void setTagDerWoche(DayOfWeek tagDerWoche) {
    this.tagDerWoche = tagDerWoche;
  }

  public LocalTime getOeffnetUm() {
    return oeffnetUm;
  }

  public void setOeffnetUm(LocalTime oeffnetUm) {
    this.oeffnetUm = oeffnetUm;
  }

  public LocalTime getSchliestUm() {
    return schliestUm;
  }

  public void setSchliestUm(LocalTime schliestUm) {
    this.schliestUm = schliestUm;
  }

  public boolean isGanztaegig() {
    return ganztaegig;
  }

  public void setGanztaegig(boolean ganztaegig) {
    this.ganztaegig = ganztaegig;
  }

  public boolean isGeschlossen() {
    return geschlossen;
  }

  public void setGeschlossen(boolean geschlossen) {
    this.geschlossen = geschlossen;
  }

  @Override
  public String toString() {
    return "AttraktionOeffnungszeit{"
            + "id=" + id
            + ", tagDerWoche=" + tagDerWoche
            + ", oeffnetUm=" + oeffnetUm
            + ", schliestUm=" + schliestUm
            + ", ganztaegig=" + ganztaegig
            + ", geschlossen=" + geschlossen
            + ", attraktion=" + attraktion
            + '}';
  }
}
