package de.hhn.se.labswp.wstgsh.webcontroller;

import de.hhn.se.labswp.wstgsh.webapi.models.registration.RegistrationAnfrage;
import de.hhn.se.labswp.wstgsh.webapi.models.registration.RegistrationAntwort;
import de.hhn.se.labswp.wstgsh.webapi.models.service.RegistrationService;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

  private final RegistrationService registrationService;

  public RegistrationController(RegistrationService registrationService) {
    this.registrationService = registrationService;
  }

  @PostMapping(path = "/register")
  public RegistrationAntwort register(@RequestBody RegistrationAnfrage anfrage) {
    return registrationService.register(anfrage);
  }

  @PostMapping(path = "/register/resendtoken")
  public RegistrationAntwort resendToken(@RequestParam("email") String email) {
    return registrationService.resendToken(email);
  }

  @GetMapping(path = "/register/confirm")
  public String confirm(@RequestParam("token") String token) {
    return registrationService.confirmToken(token);
  }
}
