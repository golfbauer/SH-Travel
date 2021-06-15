package de.hhn.se.labswp.wstgsh.webcontroller;

import de.hhn.se.labswp.wstgsh.webapi.models.Punkt;
import de.hhn.se.labswp.wstgsh.webapi.models.PunktRepository;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PunktController {

  private final PunktRepository repository;

  PunktController(PunktRepository repository) {
    this.repository = repository;
  }

  /**
   * Returns a List of every Punkt.
   * @return List of every Punkt.
   */
  @GetMapping(path = "/punkt")
  List<Punkt> all() {
    return repository.findAll();
  }

  /**
   * Returns the Punkt with the specified id.
   * @param id of the Punkt you want.
   * @return specified(id) Punkt.
   */
  @GetMapping(path = "/punkt/{id}")
  Punkt one(@PathVariable Long id) {
    return  repository.findById(id).orElseThrow(() ->
            new IllegalStateException("Id nicht gefunden."));
  }

  /**
   * Saves a new Punkt in the DB.
   * @param newPunkt New Punkt Objekt you want to save in the DB.
   * @return the just saved Punkt Object.
   */
  @PostMapping(path = "/punkt")
  Punkt newPunkt(@RequestBody Punkt newPunkt) {
    formcheckPunkt(newPunkt);
    return repository.save(newPunkt);
  }

  /**
   * Overwrites Punkt(id) with new one.
   * @param newPunkt Punkt you want to use for overwriting the previous one.
   * @param id of the Punkt you want to overwrite.
   */
  @PutMapping(path = "/punkt/{id}")
  void replacePunkt(@RequestBody Punkt newPunkt, @PathVariable Long id) {
    if (!newPunkt.getId().equals(id)) {
      throw new IllegalStateException("Neuer Punkt muss selbe id, wie der Alte haben.");
    }
    formcheckPunkt(newPunkt);
    deletePunkt(id);
    newPunkt(newPunkt);
    /*return repository.findById(id).map(Punkt -> {
      Punkt.setBreitengrad(newPunkt.getBreitengrad());
      Punkt.setLaengengrad(newPunkt.getLaengengrad());
      Punkt.setNutzerEmail(newPunkt.getNutzerEmail());
      Punkt.setName(newPunkt.getName());
      return repository.save(Punkt);
    }).orElseThrow(
            () -> new IllegalStateException("could not configure Punkt.")
    );*/
  }

  /**
   * Deletes specified Punkt.
   * @param id of the Punkt you want to delete.
   */
  @DeleteMapping(path = "/punkt/{id}")
  void deletePunkt(@PathVariable Long id) {
    repository.deleteById(id);
  }

  void formcheckPunkt(Punkt punkt){
    if (punkt.getName().length() > 30) {
      throw new IllegalStateException("Name des Punkts ist zu lang.");
    }
    if (punkt.getName()==null || punkt.getName().length() == 0) {
      throw new IllegalStateException("Name des Punkts darf nicht leer sein.");
    }
    if(punkt.getBreitengrad()==null ||punkt.getBreitengrad()>90f||punkt.getBreitengrad()<-90f){
      throw new IllegalStateException("Breitengrad des Punkts existiert nicht.");
    }
    if(punkt.getLaengengrad()==null||punkt.getLaengengrad()>180f||punkt.getLaengengrad()<-180f){
      throw new IllegalStateException("Längengrad des Punkts existiert nicht.");
    }
  }
}

