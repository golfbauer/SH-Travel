package de.hhn.se.labswp.wstgsh.webapi.models.token;

import de.hhn.se.labswp.wstgsh.webapi.models.nutzer.Nutzer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BestaetigungsToken {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String token;

  @Column(nullable = false)
  private LocalDateTime erstelltUm;

  @Column(nullable = false)
  private LocalDateTime verfaelltUm;

  private LocalDateTime beastaetigtUm;

  @ManyToOne
  @JoinColumn(
          nullable = false,
          name = "nutzer_id"
  )
  private Nutzer nutzer;

  /**
   *  Constructor for token.
   * @param token String representing token.
   * @param erstelltUm Created at.
   * @param verfaelltUm Expires at.
   * @param beastaetigtUm Confirmed at.
   * @param nutzer Nutzer to be registered.
   */
  public BestaetigungsToken(String token, LocalDateTime erstelltUm, LocalDateTime verfaelltUm,
                            LocalDateTime beastaetigtUm, Nutzer nutzer) {
    this.token = token;
    this.erstelltUm = erstelltUm;
    this.verfaelltUm = verfaelltUm;
    this.beastaetigtUm = beastaetigtUm;
    this.nutzer = nutzer;
  }

  public BestaetigungsToken(String token, LocalDateTime erstelltUm, LocalDateTime verfaelltUm,
                            Nutzer nutzer) {
    this.token = token;
    this.erstelltUm = erstelltUm;
    this.verfaelltUm = verfaelltUm;
    this.nutzer = nutzer;
  }

  public BestaetigungsToken() {

  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public LocalDateTime getErstelltUm() {
    return erstelltUm;
  }

  public void setErstelltUm(LocalDateTime erstelltUm) {
    this.erstelltUm = erstelltUm;
  }

  public LocalDateTime getVerfaelltUm() {
    return verfaelltUm;
  }

  public void setVerfaelltUm(LocalDateTime verfaelltUm) {
    this.verfaelltUm = verfaelltUm;
  }

  public LocalDateTime getBeastaetigtUm() {
    return beastaetigtUm;
  }

  public void setBeastaetigtUm(LocalDateTime beastaetigtUm) {
    this.beastaetigtUm = beastaetigtUm;
  }

  public Nutzer getNutzer() {
    return nutzer;
  }

  public void setNutzer(Nutzer nutzer) {
    this.nutzer = nutzer;
  }
}
