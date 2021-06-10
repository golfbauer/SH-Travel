package de.hhn.se.labswp.wstgsh.webcontroller;

import de.hhn.se.labswp.wstgsh.webapi.models.Attraktion;
import de.hhn.se.labswp.wstgsh.webapi.models.AttraktionOeffnungszeit;
import de.hhn.se.labswp.wstgsh.webapi.models.AttraktionRepository;

import java.time.LocalTime;
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
    return  repository.findById(id).orElseThrow(() -> new IllegalStateException("Id nicht "
            + "gefunden"));
  }

  /**
   * Saves a new Attraktion in the DB.
   * @param newAttraktion New Attraktion Objekt you want to save in the DB.
   */
  @PostMapping(path = "/attraktion")
  void newAttraktion(@RequestBody Attraktion newAttraktion) {
    List<AttraktionOeffnungszeit> oeffnungszeiten = newAttraktion.getAttraktionOeffnungszeiten();
    for (AttraktionOeffnungszeit oeffnungszeit : oeffnungszeiten) {
      oeffnungszeit.setAttraktion(newAttraktion);
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
    }
    repository.save(newAttraktion);
  }

  /**
   * Adds a new Oeffnungszeit to Attraktion.
   * @param oeffnungszeit The Oeffnungszeit to be added to Attraktion.
   * @param id ID of Attraktion.
   */
  @PostMapping(path = "/attraktion/oeffnungszeit/{id}")
  void addOeffnungszeit(@RequestBody AttraktionOeffnungszeit oeffnungszeit, @PathVariable Long id) {
    repository.findById(id).map(attraktion -> {
      oeffnungszeit.setAttraktion(attraktion);
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
  Attraktion replaceAttraktion(@RequestBody Attraktion newAttraktion, @PathVariable Long id) {
    return repository.findById(id).map(attraktion -> {
      attraktion.setBreitengrad(newAttraktion.getBreitengrad());
      attraktion.setLaengengrad(newAttraktion.getLaengengrad());
      attraktion.setNutzerEmail(newAttraktion.getNutzerEmail());
      attraktion.setName(newAttraktion.getName());
      return repository.save(attraktion);
    }).orElseThrow(
            () -> new IllegalStateException("Could not configure Attraktion.")
    );
  }

  /**
   * Deletes specified Attraktion.
   * @param id of the Attraktion you want to delete.
   */
  @DeleteMapping(path = "/attraktion/{id}")
  void deleteAttraktion(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
