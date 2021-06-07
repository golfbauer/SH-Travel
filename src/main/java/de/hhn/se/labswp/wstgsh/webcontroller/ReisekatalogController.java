package de.hhn.se.labswp.wstgsh.webcontroller;


import de.hhn.se.labswp.wstgsh.webapi.models.ReiseRepository;
import de.hhn.se.labswp.wstgsh.webapi.models.Reisekatalog;
import de.hhn.se.labswp.wstgsh.webapi.models.ReisekatalogRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ReisekatalogController {
    private final ReisekatalogRepository repository;
    private final ReiseRepository reiseRepository;

    ReisekatalogController(ReisekatalogRepository repository, ReiseRepository reiseRepository) {
        this.repository = repository;
        this.reiseRepository = reiseRepository;
    }

    @GetMapping(path = "/reisekatalog")
    List<Reisekatalog> all() {
        return repository.findAll();
    }

    @GetMapping(path = "/reisekatalog/{id}")
    Reisekatalog one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(()-> new IllegalStateException("Id nicht gefunden."));
    }

    @PostMapping(path = "/reisekatalog")
    Reisekatalog newReisekatalog(@RequestBody Reisekatalog newReisekatalog) {
        return repository.save(newReisekatalog);
    }

    @DeleteMapping(path = "/reisekatalog/{id}")
    void deleteReise(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping(path = "/reisekatalog/reise/{idReisekatalog}")
    Reisekatalog addReise(@RequestParam Long idReise, @PathVariable Long idReisekatalog) {
        return repository.findById(idReisekatalog).map(reisekatalog -> {
            reiseRepository.findById(idReise).map(reise -> {
                for (int i = 0; i < reisekatalog.getReise().size(); i++) {
                    if (reisekatalog.getId().equals(reisekatalog.getReise().get(i).getId())) {
                        throw new IllegalStateException("Reisekatalog enthält bereits Reise.");
                    }
                }
                reisekatalog.addReise(reise);
                return reiseRepository.save(reise);
            }).orElseThrow(() -> new IllegalStateException("Reise konnte nicht gespeichert werden."));
            return repository.save(reisekatalog);
        }).orElseThrow(() -> new IllegalStateException("Reise konnte nicht zu Reisekatalog hinzugefügt werden."));
    }
}
