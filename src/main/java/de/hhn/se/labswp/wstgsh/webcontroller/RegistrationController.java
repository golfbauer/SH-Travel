package de.hhn.se.labswp.wstgsh.webcontroller;

import de.hhn.se.labswp.wstgsh.webapi.models.registration.RegistrationAnfrage;
import de.hhn.se.labswp.wstgsh.webapi.models.service.RegistrationService;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

  private final RegistrationService registrationService;

  public RegistrationController(RegistrationService registrationService) {
    this.registrationService = registrationService;
  }

  @PostMapping(path = "/register")
  public String register(@RequestBody RegistrationAnfrage anfrage) {
    return registrationService.register(anfrage);
  }

  @GetMapping(path = "/register/confirm")
  public String confirm(@RequestParam("token") String token) {
    return registrationService.confirmToken(token);
  }
}
