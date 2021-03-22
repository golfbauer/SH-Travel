package de.hhn.se.labswp.wstgsh.webcontroller;

import de.hhn.se.labswp.wstgsh.webapi.models.Attraktion;
import de.hhn.se.labswp.wstgsh.webapi.models.AttraktionRepository;
import de.hhn.se.labswp.wstgsh.webapi.models.Punkt;
import de.hhn.se.labswp.wstgsh.webapi.models.PunktRepository;
import de.hhn.se.labswp.wstgsh.webapi.models.Reisepunkt;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/punkt")
public class PunktController {
  private final PunktRepository repository;
  PunktController(PunktRepository repository){
    this.repository = repository;
  }

  /**
   * Returns a List of every Punkt.
   * @return List of every Punkt.
   */
  @GetMapping(path="/all")
  List<Punkt> all() {
    return repository.findAll();
  }

  /**
   * Returns the Punkt with the specified id.
   * @param id of the Punkt you want.
   * @return specified(id) Punkt.
   */
  @GetMapping(path="/get/{id}")
  Punkt one(@PathVariable Long id) {
    return  repository.findById(id).orElseThrow(() -> new IllegalStateException("id not found."));
  }

  /**
   * Saves a new Punkt in the DB
   * @param newPunkt New Punkt Objekt you want to save in the DB.
   * @return the just saved Punkt Object.
   */
  @PostMapping(path="/create")
  Punkt newPunkt(@RequestBody Punkt newPunkt) {
    return repository.save(newPunkt);
  }

  /**
   * Overwrites Punkt(id) with new one.
   * @param newPunkt Punkt you want to use for overwriting the previous one.
   * @param id of the Punkt you want to overwrite.
   * @return the eddited Punkt.
   */
  @PutMapping(path="/edit/{id}")
  Punkt replacePunkt(@RequestBody Punkt newPunkt, @PathVariable Long id) {
    return repository.findById(id).map(Punkt -> {
      Punkt.setBreitengrad(newPunkt.getBreitengrad());
      Punkt.setLaengengrad(newPunkt.getLaengengrad());
      Punkt.setNutzerEmail(newPunkt.getNutzerEmail());
      Punkt.setName(newPunkt.getName());
      return repository.save(Punkt);
    }).orElseThrow(
            () -> new IllegalStateException("could not configure Punkt.")
    );
  }

  /**
   * Deletes specified Punkt
   * @param id of the Punkt you want to delete.
   */
  @DeleteMapping(path="/delete/{id}")
  void deleteAttraktion(@PathVariable Long id) {
    repository.deleteById(id);
  }
}

