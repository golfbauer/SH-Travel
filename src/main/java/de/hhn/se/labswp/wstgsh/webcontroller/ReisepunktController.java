package de.hhn.se.labswp.wstgsh.webcontroller;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundException;
import de.hhn.se.labswp.wstgsh.webapi.models.Reisepunkt;
import de.hhn.se.labswp.wstgsh.webapi.models.ReisepunktRepository;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
public class ReisepunktController {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktController.class);

  private final ReisepunktRepository repository;

  ReisepunktController(ReisepunktRepository repository) {
    this.repository = repository;
  }

  @GetMapping(path = "/reisepunkt")
  List<Reisepunkt> all() {
    return repository.findAll();
  }

  @GetMapping(path = "/reisepunkt/{id}")
  Reisepunkt one(@PathVariable Long id) {
    return  repository.findById(id).orElseThrow(() -> new ReisepunktNotFoundException(id));
  }

  @PostMapping(path = "/reisepunkt")
  Reisepunkt newReisepunkt(@RequestBody Reisepunkt newReisepunkt) {
    return repository.save(newReisepunkt);
  }

  @PutMapping(path = "/reisepunkt/{id}")
  Reisepunkt replaceReisepunkt(@RequestBody Reisepunkt newReisepunkt, @PathVariable Long id) {
    return repository.findById(id).map(reisepunkt -> {
      reisepunkt.setBreitengrad(newReisepunkt.getBreitengrad());
      reisepunkt.setLaengengrad(newReisepunkt.getLaengengrad());
      reisepunkt.setNutzerEmail(newReisepunkt.getNutzerEmail());
      reisepunkt.setName(newReisepunkt.getName());
      return repository.save(reisepunkt);
    }).orElseThrow(
            () -> new ReisepunktNotFoundException(id)
    );
  }

  @DeleteMapping(path = "/reisepunkt/{id}")
  void deleteReisepunkt(@PathVariable Long id) {
    repository.deleteById(id);
  }

}
