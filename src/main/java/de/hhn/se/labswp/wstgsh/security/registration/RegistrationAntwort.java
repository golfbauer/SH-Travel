package de.hhn.se.labswp.wstgsh.security.registration;

public class RegistrationAntwort {

  private String antwort;

  private String token;


  public RegistrationAntwort(String antwort, String token) {
    this.antwort = antwort;
    this.token = token;
  }

  public RegistrationAntwort() {
  }

  public String getAntwort() {
    return antwort;
  }

  public void setAntwort(String antwort) {
    this.antwort = antwort;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
