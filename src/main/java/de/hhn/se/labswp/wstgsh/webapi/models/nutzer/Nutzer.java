package de.hhn.se.labswp.wstgsh.webapi.models.nutzer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;

import de.hhn.se.labswp.wstgsh.webapi.models.Reise;
import de.hhn.se.labswp.wstgsh.webapi.models.Reisekatalog;
import de.hhn.se.labswp.wstgsh.webapi.models.Reisepunkt;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Nutzer implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String vorname;

  private String nachname;

  private String email;

  private String accountname;

  private String passwort;

  @Enumerated(EnumType.STRING)
  private NutzerRolle nutzerRolle;

  private Boolean locked;

  private Boolean enabled;

  @OneToMany(mappedBy = "nutzer", cascade = CascadeType.ALL)
  private List<Reisepunkt> reisepunkte = new ArrayList<>();

  @OneToMany(mappedBy = "nutzer", cascade = CascadeType.ALL)
  private List<Reise> reisen = new ArrayList<>();

  @OneToOne(mappedBy = "nutzer")
  private Reisekatalog reisekatalog;

  /**
   * Constructor used to create a new Nutzer, which contains all parameters.
   * @param vorname Firstname of Nutzer.
   * @param nachname Lastname of NUtzer.
   * @param email Email adress of Nutzer, which is unique
   * @param passwort Password of Nutzer.
   * @param nutzerRolle Role of Nutzer which can be Anbieter, Admin and Kunde.
   * @param locked Is account locked.
   * @param enabled Is account enabled.
   */
  public Nutzer(String vorname, String nachname, String email, String accountname, String passwort,
                NutzerRolle nutzerRolle, Boolean locked, Boolean enabled) {
    this.vorname = vorname;
    this.nachname = nachname;
    this.email = email;
    this.accountname = accountname;
    this.passwort = passwort;
    this.nutzerRolle = nutzerRolle;
    this.locked = locked;
    this.enabled = enabled;
  }

  /**
   * Creating user without booleans.
   * @param vorname Firstname of Nutzer.
   * @param nachname Lastname of NUtzer.
   * @param email Email adress of Nutzer, which is unique
   * @param passwort Password of Nutzer.
   * @param nutzerRolle Role of Nutzer which can be Anbieter, Admin and Kunde.
   */
  public Nutzer(String vorname, String nachname, String email, String accountname,
                String passwort, NutzerRolle nutzerRolle) {
    this.vorname = vorname;
    this.nachname = nachname;
    this.email = email;
    this.accountname = accountname;
    this.passwort = passwort;
    this.nutzerRolle = nutzerRolle;
    this.locked = false;
    this.enabled = false;
  }

  public Nutzer() {

  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(nutzerRolle.name());
    return Collections.singleton(authority);
  }

  @Override
  public String getPassword() {
    return passwort;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  public String getVorname() {
    return vorname;
  }

  public void setVorname(String vorname) {
    this.vorname = vorname;
  }

  public String getNachname() {
    return nachname;
  }

  public void setNachname(String nachname) {
    this.nachname = nachname;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setPasswort(String passwort) {
    this.passwort = passwort;
  }

  public NutzerRolle getNutzerRolle() {
    return nutzerRolle;
  }

  public void setNutzerRolle(NutzerRolle nutzerRolle) {
    this.nutzerRolle = nutzerRolle;
  }

  public Boolean isLocked() {
    return locked;
  }

  public void setLocked(Boolean locked) {
    this.locked = locked;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public void setAccountname(String accountname) {
    this.accountname = accountname;
  }

  public String getAccountname() {
    return accountname;
  }

  public Long getId() {
    return id;
  }

  public List<Reisepunkt> getReisepunkte() {
    return reisepunkte;
  }

  public void addReisepunkte(Reisepunkt reisepunkt) {
    this.reisepunkte.add(reisepunkt);
  }

  public void setReisepunkte(List<Reisepunkt> reisepunkte) {
    this.reisepunkte = reisepunkte;
  }

  public List<Reise> getReisen() {
    return reisen;
  }

  public void setReisen(List<Reise> reisen) {
    this.reisen = reisen;
  }

  public void addReise(Reise reise) {
    reisen.add(reise);
  }

  public Reisekatalog getReisekatalog() {
    return reisekatalog;
  }

  public void setReisekatalog(Reisekatalog reisekatalog) {
    this.reisekatalog = reisekatalog;
  }
}
