package de.hhn.se.labswp.wstgsh.webcontroller;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundException;
import de.hhn.se.labswp.wstgsh.webapi.models.Punkt;
import de.hhn.se.labswp.wstgsh.webapi.models.PunktRepository;
import java.util.List;
import java.util.Optional;
import de.hhn.se.labswp.wstgsh.webapi.models.nutzer.Nutzer;
import de.hhn.se.labswp.wstgsh.webapi.models.nutzer.NutzerRepository;
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
public class PunktController {

  private final PunktRepository repository;
  private final NutzerRepository nutzerRepository;

  PunktController(PunktRepository repository, NutzerRepository nutzerRepository) {
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
   * Returns a List of every Punkt.
   * @return List of every Punkt.
   */
  @GetMapping(path = "/punkt")
  List<Punkt> all() {
    return repository.findAll();
  }

  @GetMapping(path = "/punkt/nutzer")
  List<Punkt> allFromNutzer() {
    return findNutzer().map(nutzer ->
            repository.findAllByNutzerId(nutzer.getId()))
            .orElseThrow(() -> new IllegalStateException("Es wurde kein Punkt gefunden"));
  }

  @GetMapping(path = "/punkt/oeffentlich")
  List<Punkt> allPublic() {
    return repository.findAllByOeffentlich();
  }

  @GetMapping(path = "/punkt/nutzerOroeffentlich")
  List<Punkt> allFromNutzerOrOeffentlich() {
    return repository.findAllByOeffentlichAndNutzer(findNutzer().orElseThrow(
            () -> new IllegalStateException("Es konnte kein Nutzer gefunden werden.")
    ).getId());
  }


  /**
   * Returns the Punkt with the specified id.
   * @param id of the Punkt you want.
   * @return specified(id) Punkt.
   */
  @GetMapping(path = "/punkt/{id}")
  Punkt one(@PathVariable Long id) {
    return repository.findById(id).orElseThrow(() ->
            new IllegalStateException("Id nicht gefunden."));
  }

  @GetMapping(path = "/punkt/nutzer/{id}")
  Punkt oneFromNutzerOrPublic(@PathVariable Long id) {
    return repository.findById(id).map(punkt -> {
      if (punkt.isOeffentlich()) {
        return punkt;
      } else {
        return findNutzer().map(nutzer -> {
          if (punkt.getNutzer().getId().equals(nutzer.getId())) {
            return punkt;
          } else {
            throw new IllegalStateException("Nutzer ist nicht der Besitzer des Punkts.");
          }
        }).orElseThrow(() -> new IllegalStateException("Kein Nutzer gefunden."));
      }
    }).orElseThrow(() -> new ReisepunktNotFoundException(id));
  }

  /**
   * Saves a new Punkt in the DB.
   * @param newPunkt New Punkt Objekt you want to save in the DB.
   * @return the just saved Punkt Object.
   */
  @PostMapping(path = "/punkt/nutzer")
  Punkt newPunktWithNutzer(@RequestBody Punkt newPunkt) {
    formcheckPunkt(newPunkt);
    Nutzer nutzer = findNutzer().orElseThrow(() -> new IllegalStateException("Es konnte kein "
            + "Nutzer gefunden werden."));
    newPunkt.setNutzer(nutzer);
    nutzer.addReisepunkte(newPunkt);
    return repository.save(newPunkt);
  }

  /**
   * Overwrites Punkt(id) with new one.
   *
   * @param newPunkt Punkt you want to use for overwriting the previous one.
   * @param id       of the Punkt you want to overwrite.
   */
  @PutMapping(path = "/punkt/{id}")
  Punkt replacePunkt(@RequestBody Punkt newPunkt, @PathVariable Long id) {
    formcheckPunkt(newPunkt);
    return repository.findById(id).map(punkt -> findNutzer().map(nutzer -> {
      if (nutzer.getId().equals(punkt.getNutzer().getId())) {
        punkt.setBreitengrad(newPunkt.getBreitengrad());
        punkt.setLaengengrad(newPunkt.getLaengengrad());
        punkt.setName(newPunkt.getName());
        punkt.setOeffentlich(newPunkt.isOeffentlich());
        return repository.save(punkt);
      } else {
        throw new IllegalStateException("Nutzer hat kein Recht den Reisepunkt zu bearbeiten.");
      }
    }). orElseThrow(() -> new IllegalStateException("Nutzer nicht gefunden."))).orElseThrow(
            () -> new ReisepunktNotFoundException(id)
    );
  }

  /**
   * Deletes specified Punkt.
   *
   * @param id of the Punkt you want to delete.
   */
  // TODO: Delete Method throws exception due to foreign key from reisepunkt
  @DeleteMapping(path = "/punkt/{id}")
  void deletePunkt(@PathVariable Long id) {
    Punkt punkt = repository.findById(id).orElseThrow(() -> new IllegalStateException(
            "Reisepunkt nicht gefunden."));
    Nutzer nutzer = findNutzer().orElseThrow(() -> new IllegalStateException("Nutzer nicht "
            + "gefunden"));
    if (punkt.getNutzer().getId().equals(nutzer.getId())) {
      repository.deleteById(id);
    } else {
      throw new IllegalStateException("Nutzer hat kein Recht den Reisepunkt zu löschen.");
    }
  }

  /**
   * Checks if the given Punkt has flaws in its Attributes and Throws an
   * IllegalStateException if that's the case.
   * @param punkt Punkt you want to formcheck.
   */
  void formcheckPunkt(Punkt punkt) {
    if (punkt.getName().length() > 30) {
      throw new IllegalStateException("Name des Punkts ist zu lang.");
    }
    if (punkt.getName() == null || punkt.getName().length() == 0) {
      throw new IllegalStateException("Name des Punkts darf nicht leer sein.");
    }
    if (punkt.getBreitengrad() == null || punkt.getBreitengrad() > 90f
            || punkt.getBreitengrad() < -90f) {
      throw new IllegalStateException("Breitengrad des Punkts existiert nicht.");
    }
    if (punkt.getLaengengrad() == null || punkt.getLaengengrad() > 180f
            || punkt.getLaengengrad() < -180f) {
      throw new IllegalStateException("Längengrad des Punkts existiert nicht.");
    }
  }
}

