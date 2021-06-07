package de.hhn.se.labswp.wstgsh.webcontroller;


import de.hhn.se.labswp.wstgsh.webapi.models.ReiseRepository;
import de.hhn.se.labswp.wstgsh.webapi.models.Reisekatalog;
import de.hhn.se.labswp.wstgsh.webapi.models.ReisekatalogRepository;
import java.util.List;
import org.springframework.web.bind.annotation.*;


/**
 * Reisekataloge beinhaltet eine Liste von Reisen und gehören zu einer Nutzeremail.
 */
public class ReisekatalogController {
  private final ReisekatalogRepository repository;
  private final ReiseRepository reiseRepository;

  ReisekatalogController(ReisekatalogRepository repository, ReiseRepository reiseRepository) {
    this.repository = repository;
    this.reiseRepository = reiseRepository;
  }

  /**
   * Gibt eine Liste aller Reisekataloge zurück.
   *
   * @return Liste aller Reisen.
   */
  @GetMapping(path = "/reisekatalog")
  List<Reisekatalog> all() {
    return repository.findAll();
  }

  /**
   * Gibt den Reisekatalog mit angegebener id zurück.
   *
   * @param id des Reisekatalog den du willst.
   * @return Angegebener Reisekatalog.
   */
  @GetMapping(path = "/reisekatalog/{id}")
  Reisekatalog one(@PathVariable Long id) {
    return repository.findById(id).orElseThrow(()
            -> new IllegalStateException("Id nicht gefunden."));
  }

  /**
   * Speichert einen neuen Reisekatalog in der Datenbank.
   *
   * @param newReisekatalog Reisekatalog den du speichern möchtest.
   * @return Der gespeicherte Reisekatalog.
   */
  @PostMapping(path = "/reisekatalog")
  Reisekatalog newReisekatalog(@RequestBody Reisekatalog newReisekatalog) {
    return repository.save(newReisekatalog);
  }

  /**
   * Löscht den angegebenen Reisekatalog.
   *
   * @param id des zu löschenden Reisekatalog.
   */
  @DeleteMapping(path = "/reisekatalog/{id}")
  void deleteReise(@PathVariable Long id) {
    repository.deleteById(id);
  }

  /**
   * Fügt angegebene Reise zum angegebenen Reisekatalog hinzu.
   *
   * @param idReise        id der hinzuzufügenden Reise.
   * @param idReisekatalog id des Reisekatalogs in den du die Reise hinzufügen möchtest.
   * @return Der neue Reisekatalog mit eingespeicherter Reise.
   */
  @PutMapping(path = "/reisekatalog/reise/{idReisekatalog}")
  Reisekatalog addReise(@RequestParam Long idReise, @PathVariable Long idReisekatalog) {
    return repository.findById(idReisekatalog).map(reisekatalog -> {
      reiseRepository.findById(idReise).map(reise -> {
        for (int i = 0; i < reisekatalog.getReise().size(); i++) {
          if (reise.getId().equals(reisekatalog.getReise().get(i).getId())) {
            throw new IllegalStateException("Reisekatalog enthält bereits Reise.");
          }
        }
        reisekatalog.addReise(reise);
        return reiseRepository.save(reise);
      }).orElseThrow(() -> new IllegalStateException("Reise konnte nicht gespeichert werden."));
      return repository.save(reisekatalog);
    }).orElseThrow(()
            -> new IllegalStateException("Reise konnte nicht zu Reisekatalog hinzugefügt werden."));
  }
}
