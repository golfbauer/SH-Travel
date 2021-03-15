package de.hhn.se.labswp.wstgsh;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/")
public class PrototypController {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(PrototypController.class);
}
