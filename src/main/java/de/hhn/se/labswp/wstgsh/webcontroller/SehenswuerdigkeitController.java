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
/**
 * Controlls Sehenswuerdigkeit.
 */
@RestController
public class SehenswuerdigkeitController {
  private final SehenswuerdigkeitRepository sehenswuerdigkeitRepository;

  @Autowired
  public SehenswuerdigkeitController(SehenswuerdigkeitRepository sehenswuerdigkeitRepository) {
    this.sehenswuerdigkeitRepository = sehenswuerdigkeitRepository;
  }

  /**
   * Shows all existing Sehenswuerdigkeiten inside the database.
   * @return List of every Sehenswuerdigkeit.
   */
  @GetMapping(path = "/sehenswuerdigkeit")
  public List<Sehenswuerdigkeit> getSehenswuerdigkeiten() {
    return sehenswuerdigkeitRepository.findAll();
  }

  /**
   * Shows a Sehenswuerdigkeit with specific id.
   * @param id Request identifier to get the right Sehenswuerdigkeit.
   * @return The Sehenswuerdigkeit with spefic id.
   */
  @GetMapping(path = "/sehenswuerdigkeit/{id}")
  public Sehenswuerdigkeit getSehenswuerdigkeit(@PathVariable("id") Long id) {
    return sehenswuerdigkeitRepository.findById(id).orElseThrow(() -> new IllegalStateException(
            "ID not found"));
  }

  /**
   * Takes a Sehenswuerdigkeit object and parses it into the database.
   * @param sehenswuerdigkeit Object that is to be put into the database.
   */
  @PostMapping(path = "/sehenswuerdigkeit")
  public void newSehenswuerdigkeit(@RequestBody Sehenswuerdigkeit sehenswuerdigkeit) {
    sehenswuerdigkeitRepository.save(sehenswuerdigkeit);
  }

  /**
   * Edits a specific Sehenswuerdigkeit.
   * @param id Identification for Sehenswuerdigkeit.
   * @param newSehenswuerdigkeit Sehenswuerdigkeit with the altered information.
   */
  @PutMapping(path = "/sehenswuerdigkeit/{id}")
  @Transactional
  public void editSehenswuerdigkeit(@PathVariable("id") Long id,
                                    @RequestBody Sehenswuerdigkeit newSehenswuerdigkeit) {
    if (!newSehenswuerdigkeit.getId().equals(id)) {
      throw new IllegalStateException("New Sehenswürdigkeit must have same id as old one");
    }
    deleteSehenswuerdigkeit(id);
    newSehenswuerdigkeit(newSehenswuerdigkeit);
  }

  /**
   * Deletes a specific Sehenswuerdigkeit.
   * @param id Identification for Sehenswuerdigkeit.
   */
  @DeleteMapping(path = "/sehenswuerdigkeit/{id}")
  public void deleteSehenswuerdigkeit(@PathVariable("id") Long id) {
    sehenswuerdigkeitRepository.deleteById(id);
  }
}
