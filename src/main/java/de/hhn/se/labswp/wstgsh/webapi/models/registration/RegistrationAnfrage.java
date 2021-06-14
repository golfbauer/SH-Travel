package de.hhn.se.labswp.wstgsh.webapi.models.registration;

import java.util.Objects;

public class RegistrationAnfrage {

  private final String vorname;

  private final String nachname;

  private final String email;

  private final String accountname;

  private final String passwort;

  /**
   * Constructor containing all params.
   * @param vorname Firstname of Nutzer.
   * @param nachname Lastname of Nutzer.
   * @param email Email of Nutzer.
   * @param accountname Username of Nutzer.
   * @param passwort Password of Nutzer.
   */
  public RegistrationAnfrage(String vorname, String nachname, String email, String accountname,
                             String passwort) {
    this.vorname = vorname;
    this.nachname = nachname;
    this.email = email;
    this.accountname = accountname;
    this.passwort = passwort;
  }

  public String getVorname() {
    return vorname;
  }

  public String getNachname() {
    return nachname;
  }

  public String getEmail() {
    return email;
  }

  public String getAccountname() {
    return accountname;
  }

  public String getPasswort() {
    return passwort;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegistrationAnfrage that = (RegistrationAnfrage) o;
    return Objects.equals(vorname, that.vorname)
            && Objects.equals(nachname, that.nachname)
            && Objects.equals(email, that.email)
            && Objects.equals(accountname, that.accountname)
            && Objects.equals(passwort, that.passwort);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vorname, nachname, email, accountname, passwort);
  }

  @Override
  public String toString() {
    return "RegistrationRequest{"
            + "vorname='" + vorname + '\''
            + ", nachname='" + nachname + '\''
            + ", email='" + email + '\''
            + ", accountname='" + accountname + '\''
            + ", passwort='" + passwort + '\''
            + '}';
  }
}
