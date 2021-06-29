package de.hhn.se.labswp.wstgsh.api.webcontroller;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundException;
import de.hhn.se.labswp.wstgsh.api.models.Attraktion;
import de.hhn.se.labswp.wstgsh.api.models.AttraktionOeffnungszeit;
import de.hhn.se.labswp.wstgsh.api.models.AttraktionRepository;

import java.util.List;
import java.util.Optional;
import de.hhn.se.labswp.wstgsh.api.models.Nutzer;
import de.hhn.se.labswp.wstgsh.api.models.NutzerRepository;
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
public class AttraktionController {

  private final AttraktionRepository repository;
  private final NutzerRepository nutzerRepository;
  private final ReisepunktController reisepunktController;

  AttraktionController(AttraktionRepository repository, NutzerRepository nutzerRepository,
                       ReisepunktController reisepunktController) {
    this.repository = repository;
    this.nutzerRepository = nutzerRepository;
    this.reisepunktController = reisepunktController;
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
   * Returns a List of every Attraktion.
   * @return List of every Attraktion.
   */
  @GetMapping(path = "/attraktion")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  List<Attraktion> all() {
    return repository.findAll();
  }

  @GetMapping(path = "/attraktion/nutzer")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  List<Attraktion> allFromNutzer() {
    return findNutzer().map(nutzer ->
            repository.findAllByNutzerId(nutzer.getId()))
            .orElseThrow(() -> new IllegalStateException("Es wurde keine Attraktion gefunden"));
  }

  @GetMapping(path = "/attraktion/oeffentlich")
  List<Attraktion> allPublic() {
    return repository.findAllByOeffentlich();
  }

  @GetMapping(path = "/attraktion/nutzerOroeffentlich")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  List<Attraktion> allFromNutzerOrOeffentlich() {
    return repository.findAllByOeffentlichAndNutzer(findNutzer().orElseThrow(
            () -> new IllegalStateException("Es konnte kein Nutzer gefunden werden.")
    ).getId());
  }

  /**
   * Returns the Attraktion with the specified id.
   * @param id of the Attraktion you want.
   * @return specified(id) Attraktion.
   */
  @GetMapping(path = "/attraktion/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  Attraktion one(@PathVariable Long id) {
    return  repository.findById(id).orElseThrow(() -> new IllegalStateException("Id nicht "
            + "gefunden"));
  }

  @GetMapping(path = "/attraktion/nutzer/{id}")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  Attraktion oneFromNutzerOrPublic(@PathVariable Long id) {
    return repository.findById(id).map(attraktion -> {
      if (attraktion.isOeffentlich()) {
        return attraktion;
      } else {
        return findNutzer().map(nutzer -> {
          if (attraktion.getNutzer().getId().equals(nutzer.getId())) {
            return attraktion;
          } else {
            throw new IllegalStateException("Nutzer ist nicht der Besitzer der Attraktion.");
          }
        }).orElseThrow(() -> new IllegalStateException("Kein Nutzer gefunden."));
      }
    }).orElseThrow(() -> new ReisepunktNotFoundException(id));
  }

  /**
   * Saves a new Attraktion in the DB.
   * @param newAttraktion New Attraktion Objekt you want to save in the DB.
   */
  @PostMapping(path = "/attraktion")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  Attraktion newAttraktion(@RequestBody Attraktion newAttraktion) {
    formcheckAttraktion(newAttraktion);
    Nutzer nutzer = findNutzer().orElseThrow(() -> new IllegalStateException("Es konnte kein "
            + "Nutzer gefunden werden."));
    newAttraktion.setNutzer(nutzer);
    nutzer.addReisepunkte(newAttraktion);
    List<AttraktionOeffnungszeit> oeffnungszeiten = newAttraktion.getAttraktionOeffnungszeiten();
    for (AttraktionOeffnungszeit oeffnungszeit : oeffnungszeiten) {
      oeffnungszeit.setAttraktion(newAttraktion);
      oeffnungszeit.formcheckOeffnungszeit();
    }
    return repository.save(newAttraktion);
  }



  /**
   * Overwrites Attraktion(id) with new one.
   * @param newAttraktion Attraktion you want to use for overwriting the previous one.
   * @param id of the Attraktion you want to overwrite.
   * @return the eddited Attraktion.
   */
  @PutMapping(path = "/attraktion/{id}")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  Attraktion replaceAttraktion(@RequestBody Attraktion newAttraktion, @PathVariable Long id) {
    return repository.findById(id).map(attraktion -> findNutzer().map(nutzer -> {
      if (nutzer.getId().equals(attraktion.getNutzer().getId())) {
        attraktion.setBreitengrad(newAttraktion.getBreitengrad());
        attraktion.setLaengengrad(newAttraktion.getLaengengrad());
        attraktion.setName(newAttraktion.getName());
        attraktion.setBeschreibung(newAttraktion.getBeschreibung());
        attraktion.setOeffentlich(newAttraktion.isOeffentlich());
        attraktion.getAttraktionOeffnungszeiten().clear();
        List<AttraktionOeffnungszeit> oeffnungszeiten = newAttraktion
                .getAttraktionOeffnungszeiten();
        for (AttraktionOeffnungszeit oeffnungszeit : oeffnungszeiten) {
          oeffnungszeit.setAttraktion(attraktion);
          oeffnungszeit.formcheckOeffnungszeit();
          attraktion.getAttraktionOeffnungszeiten().add(oeffnungszeit);
        }
        return repository.save(attraktion);
      } else {
        throw new IllegalStateException("Nutzer hat kein Recht den Reisepunkt zu bearbeiten.");
      }
    }). orElseThrow(() -> new IllegalStateException("Nutzer nicht gefunden."))).orElseThrow(
            () -> new ReisepunktNotFoundException(id)
    );
  }

  /**
   * Deletes specified Attraktion.
   * @param id of the Attraktion you want to delete.
   */
  // TODO: Method wont work until all Oeffnungszeiten will be deleted first
  @DeleteMapping(path = "/attraktion/{id}")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  void deleteAttraktion(@PathVariable Long id) {
    Attraktion attraktion = repository.findById(id).orElseThrow(() -> new IllegalStateException(
            "Reisepunkt nicht gefunden."));
    Nutzer nutzer = findNutzer().orElseThrow(() -> new IllegalStateException("Nutzer nicht "
            + "gefunden"));
    if (attraktion.getNutzer().getId().equals(nutzer.getId())) {
      attraktion.getAttraktionOeffnungszeiten().clear();
      repository.deleteById(id);
      //reisepunktController.deleteReisepunkt(id);
    } else {
      throw new IllegalStateException("Nutzer hat kein Recht den Reisepunkt zu löschen.");
    }
  }

  /**
   * Adds a new Oeffnungszeit to Attraktion.
   * @param oeffnungszeit The Oeffnungszeit to be added to Attraktion.
   * @param id ID of Attraktion.
   */
  // TODO: Method has to replace the required Oeffnungszeit not add a new one
  @PostMapping(path = "/attraktion/oeffnungszeit/{id}")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER', 'ROLE_ANBIETER')")
  Attraktion addOeffnungszeit(@RequestBody AttraktionOeffnungszeit oeffnungszeit,
                              @PathVariable Long id) {
    return repository.findById(id).map(attraktion -> findNutzer().map(nutzer -> {
      if (nutzer.getId().equals(attraktion.getNutzer().getId())) {
        oeffnungszeit.setAttraktion(attraktion);
        oeffnungszeit.formcheckOeffnungszeit();
        attraktion.getAttraktionOeffnungszeiten().add(oeffnungszeit);
        return repository.save(attraktion);
      } else {
        throw new IllegalStateException("Nutzer hat kein Recht den Reisepunkt zu bearbeiten.");
      }
    }). orElseThrow(() -> new IllegalStateException("Nutzer nicht gefunden."))).orElseThrow(
            () -> new ReisepunktNotFoundException(id)
    );
  }

  /**
   * Checks if the given Attraktion has flaws in its Attributes and Throws an
   * IllegalStateException if that's the case.
   * @param attraktion Attraktion you want to formcheck.
   */
  void formcheckAttraktion(Attraktion attraktion) {
    if (attraktion.getName().length() > 30) {
      throw new IllegalStateException("Name der Attraktion ist zu lang.");
    }
    if (attraktion.getName() == null || attraktion.getName().length() == 0) {
      throw new IllegalStateException("Name der Attraktion darf nicht leer sein.");
    }
    if (attraktion.getBreitengrad() == null || attraktion.getBreitengrad() > 90f
            || attraktion.getBreitengrad() < -90f) {
      throw new IllegalStateException("Breitengrad der Attraktion existiert nicht.");
    }
    if (attraktion.getLaengengrad() == null || attraktion.getLaengengrad() > 180f
            || attraktion.getLaengengrad() < -180f) {
      throw new IllegalStateException("Längengrad der Attraktion existiert nicht.");
    }
    List<AttraktionOeffnungszeit> oeffnungszeiten = attraktion.getAttraktionOeffnungszeiten();
    for (AttraktionOeffnungszeit oeffnungszeit : oeffnungszeiten) {
      oeffnungszeit.setAttraktion(attraktion);
      if (oeffnungszeit.isGeschlossen() && oeffnungszeit.isGanztaegig()) {
        throw new IllegalStateException("Oeffnungszeit ist ganztägig und geschlossen");
      }
    }
  }
}
