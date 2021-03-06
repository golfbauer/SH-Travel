package de.hhn.se.labswp.wstgsh.api.webcontroller;


import de.hhn.se.labswp.wstgsh.api.models.*;

import java.util.List;
import java.util.Optional;

import de.hhn.se.labswp.wstgsh.api.models.Nutzer;
import de.hhn.se.labswp.wstgsh.api.models.NutzerRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


/**
 * Reisekatalog consists of a List of Reisen and is owned by one User via his UserEmail.
 */
@RestController
public class ReisekatalogController {
  private final ReisekatalogRepository repository;
  private final ReiseRepository reiseRepository;
  private final NutzerRepository nutzerRepository;

  ReisekatalogController(ReisekatalogRepository repository, ReiseRepository reiseRepository,
                         NutzerRepository nutzerRepository) {
    this.repository = repository;
    this.reiseRepository = reiseRepository;
    this.nutzerRepository = nutzerRepository;
  }

  /**
   * Finds current Nutzer.
   *
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
   * Returns a List of all Reisen.
   *
   * @return Liste aller Reisen.
   */
  @GetMapping(path = "/reisekatalog")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  List<Reisekatalog> all() {
    return repository.findAll();
  }

  /**
   * Returns specified Reisekatalog
   *
   * @param id des Reisekatalog den du willst.
   * @return Angegebener Reisekatalog.
   */
  @GetMapping(path = "/reisekatalog/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  Reisekatalog one(@PathVariable Long id) {
    return repository.findById(id).orElseThrow(()
            -> new IllegalStateException("Id nicht gefunden."));
  }

  @GetMapping(path = "/reisekatalog/nutzer/{id}")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER','ROLE_ANBIETER')")
  Reisekatalog oneFromNutzer(@PathVariable Long id) {
    Reisekatalog reisekatalog = repository.findById(id).orElseThrow(()
            -> new IllegalStateException("Id nicht gefunden."));

    return findNutzer().map(nutzer -> {
      if (nutzer.getId().equals(reisekatalog.getNutzer().getId())) {
        return reisekatalog;
      } else {
        throw new IllegalStateException("NUtzer ist nicht Besitzer des Reisekatalogs");
      }
    }).orElseThrow(() -> new IllegalStateException("Nutzer konnten icht gefunden werden."));
  }

  /**
   * Saves a new Reisekatalog in the Databank.
   *
   * @param newReisekatalog Reisekatalog den du speichern m??chtest.
   * @return Der gespeicherte Reisekatalog.
   */
  @PostMapping(path = "/reisekatalog")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER','ROLE_ANBIETER')")
  public Reisekatalog newReisekatalog(@RequestBody Reisekatalog newReisekatalog) {
    Nutzer nutzer = findNutzer().orElseThrow(() -> new IllegalStateException("Es konnte kein "
            + "Nutzer gefunden werden."));
    newReisekatalog.setNutzer(nutzer);
    nutzer.setReisekatalog(newReisekatalog);
    List<Reise> reisen = newReisekatalog.getReise();
    for (Reise reise : reisen) {
      reise.addReisekatalog(newReisekatalog);
      reiseRepository.save(reise);
    }
    return repository.save(newReisekatalog);
  }

  /**
   * Deletes specified Reisekatalog.
   *
   * @param idReisekatalog des zu l??schenden Reisekatalog.
   */
  @PutMapping(path = "/reisekatalog/rmreise/{idReisekatalog}")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER','ROLE_ANBIETER')")
  Reisekatalog deleteReiseFromReisekatalog(@PathVariable Long idReisekatalog,
                                     @RequestParam Long idReise) {
    Reisekatalog reisekatalog = repository.findById(idReisekatalog).orElseThrow(
            () -> new IllegalStateException("Reisepunkt nicht gefunden."));
    Nutzer nutzer = findNutzer().orElseThrow(() -> new IllegalStateException("Nutzer nicht "
            + "gefunden"));
    Reise reise = reiseRepository.findById(idReise).orElseThrow(() -> new IllegalStateException(
            "Konnte Reise nicht finden."));
    if (reisekatalog.getNutzer().getId().equals(nutzer.getId())) {
      reise.removeReisekatalog(reisekatalog);
      reiseRepository.save(reise);
      reisekatalog.getReise().remove(reise);
      return repository.save(reisekatalog);
    } else {
      throw new IllegalStateException("Nutzer hat kein Recht den Reisepunkt zu l??schen.");
    }
  }

  /**
   * Adds specified Reise to specified Reisekatalog.
   *
   * @param idReise        id der hinzuzuf??genden Reise.
   * @param idReisekatalog id des Reisekatalogs in den du die Reise hinzuf??gen m??chtest.
   * @return Der neue Reisekatalog mit eingespeicherter Reise.
   */
  @PutMapping(path = "/reisekatalog/reise/{idReisekatalog}")
  @PreAuthorize("hasAnyRole('ROLE_REISENDER','ROLE_ANBIETER')")
  Reisekatalog addReise(@RequestParam Long idReise, @PathVariable Long idReisekatalog) {
    Nutzer nutzer = findNutzer().orElseThrow(() -> new IllegalStateException(
            "Es konnte kein Nutzer gefunden werden."));
    Reise reise = reiseRepository.findById(idReise).orElseThrow(() -> new IllegalStateException(
            "Es konnte keine Reise gefunden werden"));
    Reisekatalog reisekatalog = repository.findById(idReisekatalog).orElseThrow(
            () -> new IllegalStateException("Es konnte kein Reisepunkt gefunden werden"));

    if (nutzer.getId().equals(reisekatalog.getNutzer().getId())) {
      for (int i = 0; i < reisekatalog.getReise().size(); i++) {
        if (reise.getId().equals(reisekatalog.getReise().get(i).getId())) {
          throw new IllegalStateException("Reisekatalog enth??lt bereits Reise.");
        }
      }
      reise.addReisekatalog(reisekatalog);
      reiseRepository.save(reise);
      reisekatalog.addReise(reise);
      return repository.save(reisekatalog);
    } else {
      throw new IllegalStateException("NUtzer ist nicht Besitzer des Reisekatalogs.");
    }
  }
}
