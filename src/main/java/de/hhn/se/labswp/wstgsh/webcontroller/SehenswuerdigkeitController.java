package de.hhn.se.labswp.wstgsh.webcontroller;


import de.hhn.se.labswp.wstgsh.webapi.models.Sehenswuerdigkeit;
import de.hhn.se.labswp.wstgsh.webapi.models.SehenswuerdigkeitRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/sehenswuerdigket")
public class SehenswuerdigkeitController {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktController.class);

  private final SehenswuerdigkeitRepository sehenswuerdigkeitRepository;

  @Autowired
  public SehenswuerdigkeitController(SehenswuerdigkeitRepository sehenswuerdigkeitRepository) {
    this.sehenswuerdigkeitRepository = sehenswuerdigkeitRepository;
  }

  /**
   * Shows all existing Sehenswuerdigkeiten inside the database.
   * @return List of every Sehenswuerdigkeit.
   */
  @GetMapping(path = "/all")
  public List<Sehenswuerdigkeit> getSehenswuerdigkeiten() {
    return sehenswuerdigkeitRepository.findAll();
  }

  /**
   * Shows a Sehenswuerdigkeit with specific id.
   * @param id Request identifier to get the right Sehenswuerdigkeit.
   * @return The Sehenswuerdigkeit with spefic id.
   */
  @GetMapping(path = "/get/{id}")
  public Sehenswuerdigkeit getSehenswuerdigkeit(@PathVariable("id") Long id) {
    return sehenswuerdigkeitRepository.findById(id).orElseThrow(() -> new IllegalStateException(
            "ID not found"));
  }

  /**
   * Takes a Sehenswuerdigkeit object and parses it into the database.
   * @param sehenswuerdigkeit Object that is to be put into the database.
   */
  @PostMapping(path = "/create")
  public void newSehenswuerdigkeit(@RequestBody Sehenswuerdigkeit sehenswuerdigkeit) {
    sehenswuerdigkeitRepository.save(sehenswuerdigkeit);
  }

  /**
   * Edits a specific Sehenswuerdigkeit.
   * @param id Identification for Sehenswuerdigkeit.
   * @param newSehenswuerdigkeit Sehenswuerdigkeit with the altered information.
   * @return The updated Sehenswuerdigkeit with the new information.
   */
  @PutMapping(path = "/edit/{id}")
  @Transactional
  public Sehenswuerdigkeit editSehenswuerdigkeit(@PathVariable("id") Long id,
                                    @RequestBody Sehenswuerdigkeit newSehenswuerdigkeit) {
    return sehenswuerdigkeitRepository.findById(id).map(sehenswuerdigkeit -> {
      sehenswuerdigkeit.setLaengengrad(newSehenswuerdigkeit.getLaengengrad());
      sehenswuerdigkeit.setBreitengrad(newSehenswuerdigkeit.getBreitengrad());
      sehenswuerdigkeit.setNutzerEmail(newSehenswuerdigkeit.getNutzerEmail());
      sehenswuerdigkeit.setName(newSehenswuerdigkeit.getName());
      sehenswuerdigkeit.setBeschreibung(newSehenswuerdigkeit.getBeschreibung());
      return sehenswuerdigkeitRepository.save(sehenswuerdigkeit);
    }).orElseThrow(() -> new IllegalStateException("Object couldnt be configured"));
  }

  /**
   * Deletes a specific Sehenswuerdigkeit.
   * @param id Identification for Sehenswuerdigkeit.
   */
  @DeleteMapping(path = "/delete/{id}")
  public void deleteSehenswuerdigkeit(@PathVariable("id") Long id) {
    sehenswuerdigkeitRepository.deleteById(id);
  }
}