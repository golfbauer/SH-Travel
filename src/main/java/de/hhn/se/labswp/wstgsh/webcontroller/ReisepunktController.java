package de.hhn.se.labswp.wstgsh.webcontroller;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundException;
import de.hhn.se.labswp.wstgsh.webapi.models.Reisepunkt;
import de.hhn.se.labswp.wstgsh.webapi.models.ReisepunktRepository;
import java.util.List;
import java.util.Optional;

import de.hhn.se.labswp.wstgsh.webapi.models.nutzer.Nutzer;
import de.hhn.se.labswp.wstgsh.webapi.models.nutzer.NutzerRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReisepunktController {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktController.class);

  private final ReisepunktRepository repository;
  private final NutzerRepository nutzerRepository;

  ReisepunktController(ReisepunktRepository repository, NutzerRepository nutzerRepository) {
    this.repository = repository;
    this.nutzerRepository = nutzerRepository;
  }

  /**
   * Finds current Nutzer.
   * @return Nutzer.
   */
  public Optional<Nutzer> findNutzer() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      String currentUserName = authentication.getName();
      return nutzerRepository.findByEmail(currentUserName);
    }
    throw new IllegalStateException("Es konnte kein angemeldeter Nutzer gefunden werden.");
  }

  @GetMapping(path = "/reisepunkt")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  List<Reisepunkt> all() {
    return repository.findAll();
  }

  @GetMapping(path = "/reisepunkt/nutzer")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  List<Reisepunkt> allFromNutzer() {
    return findNutzer().map(Nutzer::getReisepunkte).orElseThrow(
            () -> new IllegalStateException("Es wurden keine Reisepunkte gefunden.")
    );
  }

  @GetMapping(path = "/reisepunkt/nutzerOroeffentlich")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  List<Reisepunkt> allFromNutzerOrOeffentlich() {
    return repository.findAllByOeffentlichAndNutzer(findNutzer().orElseThrow(
            () -> new IllegalStateException("Es konnte kein Nutzer gefunden werden.")
    ));
  }

  @GetMapping(path = "/reisepunkt/oeffentlich")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  List<Reisepunkt> allPublic() {
    return repository.findAllByOeffentlich();
  }

  @GetMapping(path = "/reisepunkt/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  Reisepunkt one(@PathVariable Long id) {
    return  repository.findById(id).orElseThrow(() -> new ReisepunktNotFoundException(id));
  }

  @GetMapping(path = "/reisepunkt/nutzer/{id}")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  Reisepunkt oneFromNutzerOrPublic(@PathVariable Long id) {
    return repository.findById(id).map(reisepunkt -> {
      if (reisepunkt.isOeffentlich()) {
        return reisepunkt;
      } else {
        return findNutzer().map(nutzer -> {
          if (reisepunkt.getNutzer().getId().equals(nutzer.getId())) {
            return reisepunkt;
          } else {
            throw new IllegalStateException("Nutzer ist nicht der Besitzer des Reisepunkts.");
          }
        }).orElseThrow(() -> new IllegalStateException("Kein Nutzer gefunden"));
      }
    }).orElseThrow(() -> new ReisepunktNotFoundException(id));
  }

  @PostMapping(path = "/reisepunkt/nutzer")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  Reisepunkt newReisepunktWithNutzer(@RequestBody Reisepunkt newReisepunkt) {
    Nutzer nutzer = findNutzer().orElseThrow(() -> new IllegalStateException("Es konnte kein "
            + "Nutzer gefunden werden."));
    newReisepunkt.setNutzer(nutzer);
    nutzer.addReisepunkte(newReisepunkt);
    return repository.save(newReisepunkt);
  }

  @PutMapping(path = "/reisepunkt/{id}")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  Reisepunkt replaceReisepunkt(@RequestBody Reisepunkt newReisepunkt, @PathVariable Long id) {
    return repository.findById(id).map(reisepunkt -> findNutzer().map(nutzer -> {
      if (nutzer.getId().equals(reisepunkt.getNutzer().getId())) {
        reisepunkt.setBreitengrad(newReisepunkt.getBreitengrad());
        reisepunkt.setLaengengrad(newReisepunkt.getLaengengrad());
        reisepunkt.setName(newReisepunkt.getName());
        reisepunkt.setOeffentlich(newReisepunkt.isOeffentlich());
        return repository.save(reisepunkt);
      } else {
        throw new IllegalStateException("Nutzer hat kein Recht den Reisepunkt zu bearbeiten.");
      }
    }). orElseThrow(() -> new IllegalStateException("Nutzer nicht gefunden."))).orElseThrow(
            () -> new ReisepunktNotFoundException(id)
    );
  }

  // TODO: Deleting Method doesnt work due to foreign key
  @DeleteMapping(path = "/reisepunkt/{id}")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  void deleteReisepunkt(@PathVariable Long id) {
    Reisepunkt reisepunkt = repository.findById(id).orElseThrow(() -> new IllegalStateException(
            "Reisepunkt nicht gefunden."));
    Nutzer nutzer = findNutzer().orElseThrow(() -> new IllegalStateException("Nutzer nicht "
            + "gefunden"));
    if (reisepunkt.getNutzer().getId().equals(nutzer.getId())) {
      repository.deleteById(id);
    } else {
      throw new IllegalStateException("Nutzer hat kein Recht den Reisepunkt zu löschen.");
    }
  }
}
