package de.hhn.se.labswp.wstgsh.webcontroller;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundException;
import de.hhn.se.labswp.wstgsh.webapi.models.Attraktion;
import de.hhn.se.labswp.wstgsh.webapi.models.AttraktionOeffnungszeit;
import de.hhn.se.labswp.wstgsh.webapi.models.AttraktionRepository;
import java.time.LocalTime;
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
public class AttraktionController {

  private final AttraktionRepository repository;
  private final NutzerRepository nutzerRepository;

  AttraktionController(AttraktionRepository repository, NutzerRepository nutzerRepository) {
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
   * Returns a List of every Attraktion.
   * @return List of every Attraktion.
   */
  @GetMapping(path = "/attraktion")
  List<Attraktion> all() {
    return repository.findAll();
  }

  @GetMapping(path = "/attraktion/nutzer")
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
  Attraktion one(@PathVariable Long id) {
    return  repository.findById(id).orElseThrow(() -> new IllegalStateException("Id nicht "
            + "gefunden"));
  }

  @GetMapping(path = "/attraktion/nutzer/{id}")
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
  Attraktion newAttraktion(@RequestBody Attraktion newAttraktion) {
    formcheckAttraktion(newAttraktion);
    Nutzer nutzer = findNutzer().orElseThrow(() -> new IllegalStateException("Es konnte kein "
            + "Nutzer gefunden werden."));
    newAttraktion.setNutzer(nutzer);
    nutzer.addReisepunkte(newAttraktion);
    List<AttraktionOeffnungszeit> oeffnungszeiten = newAttraktion.getAttraktionOeffnungszeiten();
    for (AttraktionOeffnungszeit oeffnungszeit : oeffnungszeiten) {
      oeffnungszeit.setAttraktion(newAttraktion);
      if (oeffnungszeit.isGeschlossen() && oeffnungszeit.isGanztaegig()) {
        throw new IllegalStateException("Oeffnungszeit ist ganztägig und geschlossen");
      }
      if (oeffnungszeit.isGanztaegig()) {
        oeffnungszeit.setOeffnetUm(LocalTime.of(0, 0, 0));
        oeffnungszeit.setSchliestUm(LocalTime.of(23, 59, 0));
        oeffnungszeit.setGeschlossen(false);
      }
      if (oeffnungszeit.isGeschlossen()) {
        oeffnungszeit.setOeffnetUm(null);
        oeffnungszeit.setSchliestUm(null);
        oeffnungszeit.setGanztaegig(false);
      }
      if (oeffnungszeit.getOeffnetUm() != null && oeffnungszeit.getSchliestUm() != null) {
        oeffnungszeit.setGanztaegig(false);
        oeffnungszeit.setGeschlossen(true);
      }
    }
    return repository.save(newAttraktion);
  }

  /**
   * Adds a new Oeffnungszeit to Attraktion.
   * @param oeffnungszeit The Oeffnungszeit to be added to Attraktion.
   * @param id ID of Attraktion.
   */
  // TODO: Method has to replace the required Oeffnungszeit not add a new one
  @PostMapping(path = "/attraktion/oeffnungszeit/{id}")
  Attraktion addOeffnungszeit(@RequestBody AttraktionOeffnungszeit oeffnungszeit,
                         @PathVariable Long id) {
    return repository.findById(id).map(attraktion -> findNutzer().map(nutzer -> {
      if (nutzer.getId().equals(attraktion.getNutzer().getId())) {
        oeffnungszeit.setAttraktion(attraktion);
        if (oeffnungszeit.isGeschlossen() && oeffnungszeit.isGanztaegig()) {
          throw new IllegalStateException("Oeffnungszeit ist ganztägig und geschlossen");
        }
        if (oeffnungszeit.isGanztaegig()) {
          oeffnungszeit.setOeffnetUm(LocalTime.of(0, 0, 0));
          oeffnungszeit.setSchliestUm(LocalTime.of(23, 59, 0));
          oeffnungszeit.setGeschlossen(false);
        }
        if (oeffnungszeit.isGeschlossen()) {
          oeffnungszeit.setOeffnetUm(null);
          oeffnungszeit.setSchliestUm(null);
          oeffnungszeit.setGanztaegig(false);
        }
        if (oeffnungszeit.getOeffnetUm() != null && oeffnungszeit.getSchliestUm() != null) {
          oeffnungszeit.setGanztaegig(false);
          oeffnungszeit.setGeschlossen(true);
        }
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
   * Overwrites Attraktion(id) with new one.
   * @param newAttraktion Attraktion you want to use for overwriting the previous one.
   * @param id of the Attraktion you want to overwrite.
   * @return the eddited Attraktion.
   */
  @PutMapping(path = "/attraktion/{id}")
  Attraktion replaceAttraktion(@RequestBody Attraktion newAttraktion, @PathVariable Long id) {
    return repository.findById(id).map(attraktion -> findNutzer().map(nutzer -> {
      if (nutzer.getId().equals(attraktion.getNutzer().getId())) {
        attraktion.setBreitengrad(newAttraktion.getBreitengrad());
        attraktion.setLaengengrad(newAttraktion.getLaengengrad());
        attraktion.setName(newAttraktion.getName());
        attraktion.setBeschreibung(newAttraktion.getBeschreibung());
        attraktion.setOeffentlich(newAttraktion.isOeffentlich());
        for (int i = 0; i < attraktion.getAttraktionOeffnungszeiten().size(); i++) {
          attraktion.getAttraktionOeffnungszeiten().remove(i);
        }
        List<AttraktionOeffnungszeit> oeffnungszeiten = newAttraktion
                .getAttraktionOeffnungszeiten();
        for (AttraktionOeffnungszeit attraktionOeffnungszeit : oeffnungszeiten) {
          attraktionOeffnungszeit.setAttraktion(attraktion);
          attraktion.getAttraktionOeffnungszeiten().add(attraktionOeffnungszeit);
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
  void deleteAttraktion(@PathVariable Long id) {
    Attraktion attraktion = repository.findById(id).orElseThrow(() -> new IllegalStateException(
            "Reisepunkt nicht gefunden."));
    Nutzer nutzer = findNutzer().orElseThrow(() -> new IllegalStateException("Nutzer nicht "
            + "gefunden"));
    if (attraktion.getNutzer().getId().equals(nutzer.getId())) {
      for (int i = 0; i < attraktion.getAttraktionOeffnungszeiten().size(); i++) {
        attraktion.getAttraktionOeffnungszeiten().remove(i);
      }
      repository.deleteById(id);
    } else {
      throw new IllegalStateException("Nutzer hat kein Recht den Reisepunkt zu löschen.");
    }
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
