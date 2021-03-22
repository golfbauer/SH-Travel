package de.hhn.se.labswp.wstgsh.webcontroller;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundException;
import de.hhn.se.labswp.wstgsh.webapi.models.Attraktion;
import de.hhn.se.labswp.wstgsh.webapi.models.AttraktionRepository;
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
@RequestMapping(path = "/attraktion")
public class AttraktionController {
  private final AttraktionRepository repository;
  AttraktionController(AttraktionRepository repository){
    this.repository = repository;
  }

  /**
   * Returns a List of every Attraktion.
   * @return List of every Attraktion.
   */
  @GetMapping(path="/all")
  List<Attraktion> all() {
    return repository.findAll();
  }

  /**
   * Returns the Attraktion with the specified id.
   * @param id of the Attraktion you want.
   * @return specified(id) Attraktion.
   */
  @GetMapping(path="/get/{id}")
  Attraktion one(@PathVariable Long id) {
    return  repository.findById(id).orElseThrow(() -> new IllegalStateException("id not found."));
  }

  /**
   * Saves a new Attraktion in the DB
   * @param newAttraktion New Attraktion Objekt you want to save in the DB.
   * @return the just saved Attraktion Object.
   */
  @PostMapping(path="/create")
  Attraktion newAttraktion(@RequestBody Attraktion newAttraktion) {
    return repository.save(newAttraktion);
  }

  /**
   * Overwrites Attraktion(id) with new one.
   * @param newAttraktion Attraktion you want to use for overwriting the previous one.
   * @param id of the Attraktion you want to overwrite.
   * @return the eddited Attraktion.
   */
  @PutMapping(path="/edit/{id}")
  Reisepunkt replaceAttraktion(@RequestBody Attraktion newAttraktion, @PathVariable Long id) {
    return repository.findById(id).map(attraktion -> {
      attraktion.setBreitengrad(newAttraktion.getBreitengrad());
      attraktion.setLaengengrad(newAttraktion.getLaengengrad());
      attraktion.setNutzerEmail(newAttraktion.getNutzerEmail());
      attraktion.setName(newAttraktion.getName());
      attraktion.setPreis(newAttraktion.getPreis());
      attraktion.setBeschreibung(newAttraktion.getBeschreibung());
      return repository.save(attraktion);
    }).orElseThrow(
            () -> new IllegalStateException("could not configure Attraktion.")
    );
  }

  /**
   * Deletes specified Attraktion
   * @param id of the Attraktion you want to delete.
   */
  @DeleteMapping(path="/delete/{id}")
  void deleteAttraktion(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
