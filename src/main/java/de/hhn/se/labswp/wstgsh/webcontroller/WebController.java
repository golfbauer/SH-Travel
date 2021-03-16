package de.hhn.se.labswp.wstgsh.webcontroller;

import de.hhn.se.labswp.wstgsh.webapi.models.Reisepunkt;
import de.hhn.se.labswp.wstgsh.webapi.models.ReisepunktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="find")
public class WebController {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(WebController.class);

    private final ReisepunktService reisepunktService;

    @Autowired
    public WebController(ReisepunktService reisepunktService) {
        this.reisepunktService = reisepunktService;
    }

    @GetMapping
    public List<Reisepunkt> getReisepuntke() {
        return reisepunktService.getReisepunkte();
    }
}
