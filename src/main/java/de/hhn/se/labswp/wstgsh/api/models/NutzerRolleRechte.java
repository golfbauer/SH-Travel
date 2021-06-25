package de.hhn.se.labswp.wstgsh.api.models;

public enum NutzerRolleRechte {

  REISEPUNKT_READ("reisepunkt:read"),
  REISEPUNKT_WRITE("reisepunkt:write"),
  REISE_READ("reise:read"),
  REISE_WRITE("reise:write"),
  REISEKATALOG_READ("reisekatalog:read"),
  REISEKATALOG_WRITE("reisekatalog:write");


  private final String permission;


  NutzerRolleRechte(String permission) {
    this.permission = permission;
  }

  public String getPermission() {
    return permission;
  }
}
