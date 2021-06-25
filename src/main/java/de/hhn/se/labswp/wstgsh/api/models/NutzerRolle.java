package de.hhn.se.labswp.wstgsh.api.models;

import static de.hhn.se.labswp.wstgsh.api.models.NutzerRolleRechte.*;

import com.google.common.collect.Sets;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum NutzerRolle {
  REISENDER(Sets.newHashSet(REISEPUNKT_READ, REISEPUNKT_WRITE, REISE_READ, REISE_WRITE,
          REISEKATALOG_READ, REISEKATALOG_WRITE)),
  ANBIETER(Sets.newHashSet(REISEPUNKT_READ, REISEPUNKT_WRITE, REISE_READ, REISE_WRITE,
          REISEKATALOG_READ, REISEKATALOG_WRITE)),
  ADMIN(Sets.newHashSet());

  private final Set<NutzerRolleRechte> permissions;

  NutzerRolle(Set<NutzerRolleRechte> permissions) {
    this.permissions = permissions;
  }

  public Set<NutzerRolleRechte> getPermissions() {
    return permissions;
  }

  /**
   * Sets Roles.
   * @return Permissions for Users.
   */
  public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
    Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toSet());
    permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return permissions;
  }
}
