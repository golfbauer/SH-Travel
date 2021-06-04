package de.hhn.se.labswp.wstgsh.webcontroller;

import de.hhn.se.labswp.wstgsh.webapi.models.Reisekatalog;
import de.hhn.se.labswp.wstgsh.webapi.models.ReisekatalogRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class ReisekatalogController {
    private final ReisekatalogRepository repository;

    ReisekatalogController(ReisekatalogRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/reisekatalog")
    List<Reisekatalog> all(){return repository.findAll();}

    @GetMapping(path = "/reisekatalog/{id}")
    Optional<Reisekatalog> one(@PathVariable Long id){
        return repository.findById(id);
    }

    @PostMapping(path = "/reisekatalog")
    Reisekatalog newReisekatalog(@RequestBody Reisekatalog newReisekatalog){
        return repository.save(newReisekatalog);
    }

    @DeleteMapping(path = "/reisekatalog/{id}")
    void deleteReisepunkt(@PathVariable Long id){ repository.deleteById(id);}
}
