package de.hhn.se.labswp.wstgsh.webcontroller;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundException;
import de.hhn.se.labswp.wstgsh.webapi.models.Reise;
import de.hhn.se.labswp.wstgsh.webapi.models.ReiseRepository;
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
public class ReiseController {

  private final ReiseRepository repository;
  private final ReisepunktRepository reisepunktRepository;
  private final NutzerRepository nutzerRepository;

  ReiseController(ReiseRepository reiseRepository, ReisepunktRepository reisepunktRepository,
                  NutzerRepository nutzerRepository) {
    this.repository = reiseRepository;
    this.reisepunktRepository = reisepunktRepository;
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
   * Returns a List of every Reisen.
   *
   * @return List of every Reisen.
   */
  @GetMapping(path = "/reise")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  List<Reise> all() {
    return repository.findAll();
  }

  @GetMapping(path = "/reise/nutzer")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER','ROLE_ANBIETER')")
  List<Reise> allFromNutzer() {
    return findNutzer().map(Nutzer::getReisen).orElseThrow(
            () -> new IllegalStateException("Es konnte kein Nutzer gefunden werden."));
  }

  @GetMapping(path = "/reise/nutzerOroeffentlich")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER','ROLE_ANBIETER')")
  List<Reise> allFromNutzerOrOeffentlich() {
    return repository.findAllByOeffentlichAndNutzer(findNutzer().orElseThrow(
            () -> new IllegalStateException("Es konnte kein Nutzer gefunden werden.")
    ));
  }

  @GetMapping(path = "/reise/oeffentlich")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER','ROLE_ANBIETER')")
  List<Reise> allPublic() {
    return repository.findAllByOeffentlich();
  }

  /**
   * Returns the Reise with the specified id.
   *
   * @param id of the Reise you want.
   * @return specified(id) Reise.
   */
  @GetMapping(path = "/reise/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  Reise one(@PathVariable Long id) {
    return repository.findById(id).orElseThrow(() ->
            new IllegalStateException("Id nicht gefunden."));
  }

  @GetMapping(path = "/reise/nutzer/{id}")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER','ROLE_ANBIETER')")
  Reise oneFromNutzerOrPublic(@PathVariable Long id) {
    return repository.findById(id).map(reise -> {
      if (reise.isOeffentlich()) {
        return reise;
      } else {
        return findNutzer().map(nutzer -> {
          if (reise.getNutzer().getId().equals(nutzer.getId())) {
            return reise;
          } else {
            throw new IllegalStateException("Nutzer ist nicht der Besitzer des Reisepunkts.");
          }
        }).orElseThrow(() -> new IllegalStateException("Kein Nutzer gefunden"));
      }
    }).orElseThrow(() -> new ReisepunktNotFoundException(id));
  }


  /**
   * Saves a new Reise in the DB
   *
   * @param newReise New Reise Objekt you want to save in the DB.
   * @return the just saved Reise Object.
   */
  // TODO: Check if Reise can be created containing Reisepunkte
  @PostMapping(path = "/reise")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER','ROLE_ANBIETER')")
  Reise newReise(@RequestBody Reise newReise) {
    formcheckReise(newReise);
    Nutzer nutzer = findNutzer().orElseThrow(() -> new IllegalStateException("Es konnte kein "
            + "Nutzer gefunden werden."));
    newReise.setNutzer(nutzer);
    nutzer.addReise(newReise);
    for (int i = 0; i < newReise.getReisepunkte().size(); i++) {
      reisepunktRepository.findById(newReise.getReisepunkte().get(i).getId()).map(reisepunkt -> {
        reisepunkt.addReise(newReise);
        if (reisepunkt.getNutzer() == null) {
          reisepunkt.setNutzer(nutzer);
        }
        return reisepunktRepository.save(reisepunkt);
      });
    }
    return repository.save(newReise);
  }

  /**
   * Overwrites Reise(id) with new one.
   *
   * @param newReise Reise you want to use for overwriting the previous one.
   * @param id       of the Reise you want to overwrite.
   * @return the eddited Reise.
   */
  // TODO: Check if Reise can be replaced containing Reisepunkte
  @PutMapping(path = "/reise/{id}")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER','ROLE_ANBIETER')")
  Reise replaceReise(@RequestBody Reise newReise, @PathVariable Long id) {
    formcheckReise(newReise);
    return repository.findById(id).map(reise -> findNutzer().map(nutzer -> {
      if (nutzer.getId().equals(reise.getNutzer().getId())) {
        reise.setName(newReise.getName());
        reise.setTermin(newReise.getTermin());
        reise.setOeffentlich(newReise.getOeffentlich());

        reise.getReisepunkte().clear();

        List<Reisepunkt> reisepunkte = newReise.getReisepunkte();
        for (Reisepunkt reisepunkt : reisepunkte) {
          reise.addReisepunkt(reisepunkt);
          if (!reisepunkt.getReisen().contains(reise)) {
            reisepunkt.addReise(reise);
          }
          if (reisepunkt.getNutzer() == null) {
            reisepunkt.setNutzer(nutzer);
          }
        }

        return repository.save(reise);
      } else {
        throw new IllegalStateException("Nutzer ist nicht besitzer der Reise.");
      }
    }).orElseThrow(() -> new IllegalStateException("Es konnte kein Nutzer gefunden werden.")))
            .orElseThrow(() -> new IllegalStateException("Konnte Reise nicht verändern."));
  }

  /**
   * Deletes specified Reise.
   *
   * @param id of the Reise you want to delete.
   */
  // TODO: Deleting a Reise will throw an error due to foreign keys
  @DeleteMapping(path = "/reise/{id}")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER','ROLE_ANBIETER')")
  void deleteReisepunkt(@PathVariable Long id) {
    Reise reise = repository.findById(id).orElseThrow(() -> new IllegalStateException(
            "Reisepunkt nicht gefunden."));
    Nutzer nutzer = findNutzer().orElseThrow(() -> new IllegalStateException("Nutzer nicht "
            + "gefunden"));
    if (reise.getNutzer().getId().equals(nutzer.getId())) {
      repository.deleteById(id);
    } else {
      throw new IllegalStateException("Nutzer hat kein Recht den Reisepunkt zu löschen.");
    }
  }

  /**
   * Adds a new Reisepunkt to the Reise. Both have to exist in the Database already.
   * Will throw a Exception if Reise already contains same Reisepunkt.
   *
   * @param idReisepunkt ID of the Reisepunkt.
   * @param idReise      ID of the Reise.
   * @return Configured Reise with new Reisepunkt.
   */
  @PutMapping(path = "/reise/reisepunkt/{idReise}")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER','ROLE_ANBIETER')")
  Reise addReisepunkt(@RequestParam Long idReisepunkt, @PathVariable Long idReise) {
    Nutzer nutzer = findNutzer().orElseThrow(() -> new IllegalStateException(
            "Es konnte kein Nutzer gefunden werden."));
    Reise reise = repository.findById(idReise).orElseThrow(() -> new IllegalStateException(
            "Es konnte keine Reise gefunden werden"));
    Reisepunkt reisepunkt = reisepunktRepository.findById(idReisepunkt).orElseThrow(
            () -> new IllegalStateException("Es konnte kein Reisepunkt gefunden werden"));

    if (nutzer.getId().equals(reise.getNutzer().getId())) {
      for (int i = 0; i < reise.getReisepunkte().size(); i++) {
        if (reisepunkt.getId().equals(reise.getReisepunkte().get(i).getId())) {
          throw new IllegalStateException("Reisepunkt ist bereits in Reise vorhanden.");
        }
      }
      reise.addReisepunkt(reisepunkt);
      reisepunkt.addReise(reise);
      reisepunktRepository.save(reisepunkt);
    } else {
      throw new IllegalStateException("NUtzer ist nicht Besitzer der Reise.");
    }
    return repository.save(reise);
  }

  /**
   * Changes the Privacy inside a Reise, by changing the boolean oeffentlich.
   *
   * @param oeffentlich To be changed boolean true = oeffentlich, false = privat.
   * @param id          ID of the Reise.
   * @return Configured Reise with altered Privacy Setting.
   */
  @PutMapping(path = "/reise/oeffentlich/{id}")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER','ROLE_ANBIETER')")
  Reise changePrivacySetting(@RequestParam boolean oeffentlich, @PathVariable Long id) {
    return repository.findById(id).map(reise -> findNutzer().map(nutzer -> {
      if (nutzer.getId().equals(reise.getNutzer().getId())) {
        reise.setOeffentlich(oeffentlich);
        return repository.save(reise);
      } else {
        throw new IllegalStateException("Nutzer ist nicht Besitzer der Reise.");
      }
    }).orElseThrow(() -> new IllegalStateException("Konnte Nutzer nicht finden.")))
            .orElseThrow(() -> new IllegalStateException(
                    "Konnte Oeffentlichkeit nicht verändern."));
  }

  /**
   * Checks if the given Reise has flaws in its Attributes and Throws an
   * IllegalStateException if that's the case.
   * @param reise Reise you want to formcheck.
   */
  void formcheckReise(Reise reise) {
    if (reise.getName() == null || reise.getName().length() == 0) {
      throw new IllegalStateException("Name der Reise darf nicht leer bleiben.");
    }
    if (reise.getName().length() > 50) {
      throw new IllegalStateException("Name der Reise darf nicht länger als 50 Zeichen sein.");
    }
  }
}

