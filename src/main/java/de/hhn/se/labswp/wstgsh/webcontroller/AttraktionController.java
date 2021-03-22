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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttraktionController {
  private final AttraktionRepository repository;
  AttraktionController(AttraktionRepository repository){
    this.repository = repository;
  }

  @GetMapping(path="/attraktion")
  List<Attraktion> all() {
    return repository.findAll();
  }

  @GetMapping(path="/attraktion/{id}")
  Attraktion one(@PathVariable Long id) {
    return  repository.findById(id).orElseThrow(() -> new ReisepunktNotFoundException(id));
  }

  @PostMapping(path="/attraktion")
  Attraktion newAttraktion(@RequestBody Attraktion newAttraktion) {
    return repository.save(newAttraktion);
  }

  @PutMapping(path="/attraktion/{id}")
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
            () -> new ReisepunktNotFoundException(id)
    );
  }

  @DeleteMapping(path="/attraktion/{id}")
  void deleteAttraktion(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
