package de.hhn.se.labswp.wstgsh.webcontroller;


import de.hhn.se.labswp.wstgsh.webapi.models.Sehenswuerdigkeit;
import de.hhn.se.labswp.wstgsh.webapi.models.SehenswuerdigkeitRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SehenswuerdigkeitController {
  private final SehenswuerdigkeitRepository sehenswuerdigkeitRepository;

  @Autowired
  public SehenswuerdigkeitController(SehenswuerdigkeitRepository sehenswuerdigkeitRepository) {
    this.sehenswuerdigkeitRepository = sehenswuerdigkeitRepository;
  }

  /**
   * Shows all existing Sehenswuerdigkeiten inside the database.
   *
   * @return List of every Sehenswuerdigkeit.
   */
  @GetMapping(path = "/sehenswuerdigkeit")
  public List<Sehenswuerdigkeit> getSehenswuerdigkeiten() {
    return sehenswuerdigkeitRepository.findAll();
  }

  /**
   * Shows a Sehenswuerdigkeit with specific id.
   *
   * @param id Request identifier to get the right Sehenswuerdigkeit.
   * @return The Sehenswuerdigkeit with spefic id.
   */
  @GetMapping(path = "/sehenswuerdigkeit/{id}")
  public Sehenswuerdigkeit getSehenswuerdigkeit(@PathVariable("id") Long id) {
    return sehenswuerdigkeitRepository.findById(id).orElseThrow(() -> new IllegalStateException(
        "ID nicht gefunden."));
  }

  /**
   * Takes a Sehenswuerdigkeit object and parses it into the database.
   *
   * @param sehenswuerdigkeit Object that is to be put into the database.
   */
  @PostMapping(path = "/sehenswuerdigkeit")
  public void newSehenswuerdigkeit(@RequestBody Sehenswuerdigkeit sehenswuerdigkeit) {
    formcheckSehenswuerdigkeit(sehenswuerdigkeit);
    sehenswuerdigkeitRepository.save(sehenswuerdigkeit);
  }

  /**
   * Edits a specific Sehenswuerdigkeit.
   *
   * @param id                   Identification for Sehenswuerdigkeit.
   * @param newSehenswuerdigkeit Sehenswuerdigkeit with the altered information.
   */
  @PutMapping(path = "/sehenswuerdigkeit/{id}")
  //@Transactional
  public Sehenswuerdigkeit editSehenswuerdigkeit(@PathVariable("id") Long id,
                                                 @RequestBody Sehenswuerdigkeit newSehenswuerdigkeit) {
    return sehenswuerdigkeitRepository.findById(id).map(Sehenswuerdigkeit -> {
      Sehenswuerdigkeit.setBreitengrad(newSehenswuerdigkeit.getBreitengrad());
      Sehenswuerdigkeit.setLaengengrad(newSehenswuerdigkeit.getLaengengrad());
      Sehenswuerdigkeit.setNutzerEmail(newSehenswuerdigkeit.getNutzerEmail());
      Sehenswuerdigkeit.setName(newSehenswuerdigkeit.getName());
      Sehenswuerdigkeit.setBeschreibung(newSehenswuerdigkeit.getBeschreibung());
      return sehenswuerdigkeitRepository.save(Sehenswuerdigkeit);
    }).orElseThrow(
        () -> new IllegalArgumentException("could not configure Sehenswürdigkeit.")
    );
  }

  /**
   * Checks if the given Sehenswürdigkeit has flaws in its Attributes and Throws an
   * IllegalStateException if that's the case.
   *
   * @param sehenswuerdigkeit you want to formcheck.
   */
  void formcheckSehenswuerdigkeit(Sehenswuerdigkeit sehenswuerdigkeit) {
    if (sehenswuerdigkeit.getName().length() > 30) {
      throw new IllegalStateException("Name der Sehenswürdigkeit ist zu lang.");
    }
    if (sehenswuerdigkeit.getName() == null || sehenswuerdigkeit.getName().length() == 0) {
      throw new IllegalStateException("Name der Sehenswurdigkeit darf nicht leer sein.");
    }
    if (sehenswuerdigkeit.getBreitengrad() == null || sehenswuerdigkeit.getBreitengrad() > 90f
        || sehenswuerdigkeit.getBreitengrad() < -90f) {
      throw new IllegalStateException("Breitengrad der Sehenswurdigkeit existiert nicht.");
    }
    if (sehenswuerdigkeit.getLaengengrad() == null || sehenswuerdigkeit.getLaengengrad() > 180f
        || sehenswuerdigkeit.getLaengengrad() < -180f) {
      throw new IllegalStateException("Längengrad der Sehenswurdigkeit existiert nicht.");
    }
  }
}
