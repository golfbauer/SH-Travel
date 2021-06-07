package de.hhn.se.labswp.wstgsh.webcontroller;

import de.hhn.se.labswp.wstgsh.webapi.models.Attraktion;
import de.hhn.se.labswp.wstgsh.webapi.models.AttraktionOeffnungszeit;
import de.hhn.se.labswp.wstgsh.webapi.models.AttraktionRepository;
import java.util.List;
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

  AttraktionController(AttraktionRepository repository) {
    this.repository = repository;
  }

  /**
   * Returns a List of every Attraktion.
   * @return List of every Attraktion.
   */
  @GetMapping(path = "/attraktion")
  List<Attraktion> all() {
    return repository.findAll();
  }

  /**
   * Returns the Attraktion with the specified id.
   * @param id of the Attraktion you want.
   * @return specified(id) Attraktion.
   */
  @GetMapping(path = "/attraktion/{id}")
  Attraktion one(@PathVariable Long id) {
    return  repository.findById(id).orElseThrow(() -> new IllegalStateException("Id not found."));
  }

  /**
   * Saves a new Attraktion in the DB
   * @param newAttraktion New Attraktion Objekt you want to save in the DB.
   * @return the just saved Attraktion Object.
   */
  @PostMapping(path = "/attraktion")
  Attraktion newAttraktion(@RequestBody Attraktion newAttraktion) {
    return repository.save(newAttraktion);
  }

  @PostMapping(path = "/attraktion/oeffnungszeit/{id}")
  void addOeffnungszeit(@RequestBody String string, @PathVariable Long id) {
    repository.findById(id).map(attraktion -> {
      AttraktionOeffnungszeit oeffnungszeit = new AttraktionOeffnungszeit(string, attraktion);
      attraktion.getAttraktionOeffnungszeiten().add(oeffnungszeit);
      return repository.save(attraktion);
    });
  }

  /**
   * Overwrites Attraktion(id) with new one.
   * @param newAttraktion Attraktion you want to use for overwriting the previous one.
   * @param id of the Attraktion you want to overwrite.
   * @return the eddited Attraktion.
   */
  @PutMapping(path = "/attraktion/{id}")
  void replaceAttraktion(@RequestBody Attraktion newAttraktion, @PathVariable Long id) {
    if (!newAttraktion.getId().equals(id)) {
      throw new IllegalStateException("New Attraktion must have same id as old one");
    }
    deleteAttraktion(id);
    newAttraktion(newAttraktion);
  }

  /**
   * Deletes specified Attraktion
   * @param id of the Attraktion you want to delete.
   */
  @DeleteMapping(path = "/attraktion/{id}")
  void deleteAttraktion(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
