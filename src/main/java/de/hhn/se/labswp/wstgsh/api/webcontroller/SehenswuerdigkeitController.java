package de.hhn.se.labswp.wstgsh.api.webcontroller;


import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundException;
import de.hhn.se.labswp.wstgsh.api.models.Sehenswuerdigkeit;
import de.hhn.se.labswp.wstgsh.api.models.SehenswuerdigkeitRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import de.hhn.se.labswp.wstgsh.api.models.Nutzer;
import de.hhn.se.labswp.wstgsh.api.models.NutzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SehenswuerdigkeitController {

  private final SehenswuerdigkeitRepository repository;
  private final NutzerRepository nutzerRepository;

  @Autowired
  public SehenswuerdigkeitController(SehenswuerdigkeitRepository repository,
                                     NutzerRepository nutzerRepository) {
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

  /**
   * Shows all existing Sehenswuerdigkeiten inside the database.
   *
   * @return List of every Sehenswuerdigkeit.
   */
  @GetMapping(path = "/sehenswuerdigkeit")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<Sehenswuerdigkeit> getSehenswuerdigkeiten() {
    return repository.findAll();
  }

  @GetMapping(path = "/sehenswuerdigkeit/nutzer")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  List<Sehenswuerdigkeit> allFromNutzer() {
    return findNutzer().map(nutzer ->
            repository.findAllByNutzerId(nutzer.getId()))
            .orElseThrow(() -> new IllegalStateException("Es wurde kein Punkt gefunden"));
  }

  @GetMapping(path = "/sehenswuerdigkeit/oeffentlich")
  List<Sehenswuerdigkeit> allPublic() {
    return repository.findAllByOeffentlich();
  }

  @GetMapping(path = "/sehenswuerdigkeit/nutzerOrOeffentlich")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  List<Sehenswuerdigkeit> allFromNutzerOrOeffentlich() {
    return repository.findAllByOeffentlichAndNutzer(findNutzer().orElseThrow(
            () -> new IllegalStateException("Es konnte kein Nutzer gefunden werden.")
    ).getId());
  }

  /**
   * Shows a Sehenswuerdigkeit with specific id.
   *
   * @param id Request identifier to get the right Sehenswuerdigkeit.
   * @return The Sehenswuerdigkeit with spefic id.
   */
  @GetMapping(path = "/sehenswuerdigkeit/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public Sehenswuerdigkeit getSehenswuerdigkeit(@PathVariable("id") Long id) {
    return repository.findById(id).orElseThrow(() -> new IllegalStateException(
            "ID nicht gefunden."));
  }

  @GetMapping(path = "/sehenswuerdigkeit/nutzer/{id}")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  Sehenswuerdigkeit oneFromNutzerOrPublic(@PathVariable Long id) {
    return repository.findById(id).map(sehenswuerdigkeit -> {
      if (sehenswuerdigkeit.isOeffentlich()) {
        return sehenswuerdigkeit;
      } else {
        return findNutzer().map(nutzer -> {
          if (sehenswuerdigkeit.getNutzer().getId().equals(nutzer.getId())) {
            return sehenswuerdigkeit;
          } else {
            throw new IllegalStateException("Nutzer ist nicht der Besitzer des Punkts.");
          }
        }).orElseThrow(() -> new IllegalStateException("Kein Nutzer gefunden."));
      }
    }).orElseThrow(() -> new ReisepunktNotFoundException(id));
  }

  /**
   * Takes a Sehenswuerdigkeit object and parses it into the database.
   */
  @PostMapping(path = "/sehenswuerdigkeit")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  Sehenswuerdigkeit newSehenswuerdigkeitWithNutzer(
          @RequestBody Sehenswuerdigkeit newSehenswuerdigkeit) {
    formcheckSehenswuerdigkeit(newSehenswuerdigkeit);
    Nutzer nutzer = findNutzer().orElseThrow(() -> new IllegalStateException("Es konnte kein "
            + "Nutzer gefunden werden."));
    newSehenswuerdigkeit.setNutzer(nutzer);
    nutzer.addReisepunkte(newSehenswuerdigkeit);
    return repository.save(newSehenswuerdigkeit);
  }

  /**
   * Edits a specific Sehenswuerdigkeit.
   *
   * @param id                   Identification for Sehenswuerdigkeit.
   * @param newSehenswuerdigkeit Sehenswuerdigkeit with the altered information.
   */
  @PutMapping(path = "/sehenswuerdigkeit/{id}")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  @Transactional
  Sehenswuerdigkeit replaceSehenswuerdigkeit(@RequestBody Sehenswuerdigkeit newSehenswuerdigkeit,
                             @PathVariable Long id) {
    formcheckSehenswuerdigkeit(newSehenswuerdigkeit);
    return repository.findById(id).map(sehenswuerdigkeit -> findNutzer().map(nutzer -> {
      if (nutzer.getId().equals(sehenswuerdigkeit.getNutzer().getId())) {
        sehenswuerdigkeit.setBreitengrad(newSehenswuerdigkeit.getBreitengrad());
        sehenswuerdigkeit.setLaengengrad(newSehenswuerdigkeit.getLaengengrad());
        sehenswuerdigkeit.setName(newSehenswuerdigkeit.getName());
        sehenswuerdigkeit.setOeffentlich(newSehenswuerdigkeit.isOeffentlich());
        sehenswuerdigkeit.setBeschreibung(newSehenswuerdigkeit.getBeschreibung());
        return repository.save(sehenswuerdigkeit);
      } else {
        throw new IllegalStateException("Nutzer hat kein Recht den Reisepunkt zu bearbeiten.");
      }
    }). orElseThrow(() -> new IllegalStateException("Nutzer nicht gefunden."))).orElseThrow(
            () -> new ReisepunktNotFoundException(id)
    );
  }

  /**
   * Deletes a specific Sehenswuerdigkeit.
   *
   * @param id Identification for Sehenswuerdigkeit.
   */
  // TODO: Deleting Method doesnt work due to foreign key
  @DeleteMapping(path = "/sehenswuerdigkeit/{id}")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  void deleteSehenswuerdigkeit(@PathVariable Long id) {
    Sehenswuerdigkeit sehenswuerdigkeit = repository.findById(id).orElseThrow(
            () -> new IllegalStateException("Reisepunkt nicht gefunden."));
    Nutzer nutzer = findNutzer().orElseThrow(() -> new IllegalStateException("Nutzer nicht "
            + "gefunden"));
    if (sehenswuerdigkeit.getNutzer().getId().equals(nutzer.getId())) {
      repository.deleteById(id);
    } else {
      throw new IllegalStateException("Nutzer hat kein Recht den Reisepunkt zu löschen.");
    }
  }

  /**
   * Checks if the given Sehenswürdigkeit has flaws in its Attributes and Throws an
   * IllegalStateException if that's the case.
   * @param sehenswuerdigkeit you want to formcheck.
   */
  void formcheckSehenswuerdigkeit(Sehenswuerdigkeit sehenswuerdigkeit) {
    if (sehenswuerdigkeit.getName().length() > 30) {
      throw new IllegalStateException("Name der Sehenswürdigkeit ist zu lang.");
    }
    if (sehenswuerdigkeit.getName() == null || sehenswuerdigkeit.getName().length() == 0) {
      throw new IllegalStateException("Name der Sehenswurdigkeit darf nicht leer sein.");
    }
    if (sehenswuerdigkeit.getBreitengrad() == null || sehenswuerdigkeit.getBreitengrad() > 90f
            || sehenswuerdigkeit.getBreitengrad() < -90f) {
      throw new IllegalStateException("Breitengrad der Sehenswurdigkeit existiert nicht.");
    }
    if (sehenswuerdigkeit.getLaengengrad() == null || sehenswuerdigkeit.getLaengengrad() > 180f
            || sehenswuerdigkeit.getLaengengrad() < -180f) {
      throw new IllegalStateException("Längengrad der Sehenswurdigkeit existiert nicht.");
    }
  }
}
