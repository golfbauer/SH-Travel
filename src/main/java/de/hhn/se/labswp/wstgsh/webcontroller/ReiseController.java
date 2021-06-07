package de.hhn.se.labswp.wstgsh.webcontroller;

import de.hhn.se.labswp.wstgsh.webapi.models.Reise;
import de.hhn.se.labswp.wstgsh.webapi.models.ReiseRepository;
import de.hhn.se.labswp.wstgsh.webapi.models.ReisepunktRepository;
import java.util.List;
import org.springframework.web.bind.annotation.*;


@RestController
public class ReiseController {

  private final ReiseRepository repository;
  private final ReisepunktRepository reisepunktRepository;

  ReiseController(ReiseRepository reiseRepository, ReisepunktRepository reisepunktRepository) {
    this.repository = reiseRepository;
    this.reisepunktRepository = reisepunktRepository;
  }

  /**
   * Returns a List of every Reisen.
   * @return List of every Reisen.
   */
  @GetMapping(path = "/reise")
  List<Reise> all() {
    return repository.findAll();
  }

  /**
   * Returns the Reise with the specified id.
   * @param id of the Reise you want.
   * @return specified(id) Reise.
   */
  @GetMapping(path = "/reise/{id}")
  Reise one(@PathVariable Long id) {
    return  repository.findById(id).orElseThrow(() ->
            new IllegalStateException("Id nicht gefunden."));
  }

  /**
   * Saves a new Reise in the DB
   * @param newReise New Reise Objekt you want to save in the DB.
   * @return the just saved Reise Object.
   */
  @PostMapping(path = "/reise")
  Reise newReise(@RequestBody Reise newReise) {
    return repository.save(newReise);
  }

  /**
   * Overwrites Reise(id) with new one.
   * @param newReise Reise you want to use for overwriting the previous one.
   * @param id of the Reise you want to overwrite.
   * @return the eddited Reise.
   */
  @PutMapping(path = "/reise/{id}")
  Reise replaceReise(@RequestBody Reise newReise, @PathVariable Long id) {
    return repository.findById(id).map(reise -> {
      reise.setName(newReise.getName());
      reise.setTermin(newReise.getTermin());
      reise.setOeffentlich(newReise.getOeffentlich());
      reise.setReisepunkte(newReise.getReisepunkte());
      reise.setReisekatalog(newReise.getReisekatalog());
      return repository.save(reise);
    }).orElseThrow(() -> new IllegalStateException("Konnte Reise nicht verändern."));
  }

  /**
   * Deletes specified Reise.
   * @param id of the Reise you want to delete.
   */
  @DeleteMapping(path = "/reise/{id}")
  void deleteReise(@PathVariable Long id) {
    repository.deleteById(id);
  }

  /**
   * Adds a new Reisepunkt to the Reise. Both have to exist in the Database already.
   * Will throw a Exception if Reise already contains same Reisepunkt.
   * @param idReisepunkt ID of the Reisepunkt.
   * @param idReise ID of the Reise.
   * @return Configured Reise with new Reisepunkt.
   */
  @PutMapping(path = "/reise/reisepunkt/{idReise}")
  Reise addReisepunkt(@RequestParam Long idReisepunkt, @PathVariable Long idReise) {
    return repository.findById(idReise).map(reise -> {
      reisepunktRepository.findById(idReisepunkt).map(reisepunkt -> {
        for (int i = 0; i < reise.getReisepunkte().size(); i++) {
          if (reisepunkt.getId().equals(reise.getReisepunkte().get(i).getId())) {
            throw new IllegalStateException("Reisepunkt ist bereits in Reise vorhanden.");
          }
        }
        reise.addReisepunkt(reisepunkt);
        return reisepunktRepository.save(reisepunkt);
      }).orElseThrow(() -> new IllegalStateException("Could not save Reisepunkt"));
      return repository.save(reise);
    }).orElseThrow(() -> new IllegalStateException("Could not add Reisepunkt to Reise"));
  }

  /**
   * Changes the Privacy inside a Reise, by changing the boolean oeffentlich.
   * @param oeffentlich To be changed boolean true = oeffentlich, false = privat.
   * @param id ID of the Reise.
   * @return Configured Reise with altered Privacy Setting.
   */
  @PutMapping(path = "/reise/oeffentlich/{id}")
  Reise changePrivacySetting(@RequestParam boolean oeffentlich, @PathVariable Long id) {
    return repository.findById(id).map(reise -> {
      reise.setOeffentlich(oeffentlich);
      return repository.save(reise);
    }).orElseThrow(() -> new IllegalStateException("Konnte Oeffentlichkeit nicht verändern."));
  }
}

