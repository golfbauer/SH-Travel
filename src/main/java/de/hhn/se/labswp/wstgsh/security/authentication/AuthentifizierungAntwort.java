package de.hhn.se.labswp.wstgsh.security.authentication;

import de.hhn.se.labswp.wstgsh.api.models.NutzerRolle;

public class AuthentifizierungAntwort {

  private final String token;
  private final String username;
  private final String accountname;
  private final String vorname;
  private final String nachname;
  private final String nutzerRolle;
  private final Long id;

  public AuthentifizierungAntwort(String token, String username, String accountname, String vorname,
                                  String nachname, String nutzerRolle, Long id) {
    this.token = token;
    this.username = username;
    this.accountname = accountname;
    this.vorname = vorname;
    this.nachname = nachname;
    this.nutzerRolle = nutzerRolle;
    this.id = id;
  }


  public String getUsername() {
    return username;
  }

  public String getAccountname() {
    return accountname;
  }

  public String getVorname() {
    return vorname;
  }

  public String getNachname() {
    return nachname;
  }

  public String getToken() {
    return token;
  }

  public String getNutzerRolle() {
    return nutzerRolle;
  }

  public Long getId() {
    return id;
  }
}
