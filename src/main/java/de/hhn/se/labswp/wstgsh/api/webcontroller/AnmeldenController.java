package de.hhn.se.labswp.wstgsh.api.webcontroller;

import de.hhn.se.labswp.wstgsh.api.models.Nutzer;
import de.hhn.se.labswp.wstgsh.api.service.NutzerService;
import de.hhn.se.labswp.wstgsh.security.authentication.AuthentifizierungAnfrage;
import de.hhn.se.labswp.wstgsh.security.authentication.AuthentifizierungAntwort;
import de.hhn.se.labswp.wstgsh.security.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnmeldenController {

  private final AuthenticationManager authenticationManager;

  private final NutzerService nutzerService;

  private final JwtUtil jwtTokenUtil;

  // TODO: write JAVA DOC
  public AnmeldenController(AuthenticationManager authenticationManager,
                            NutzerService nutzerService, JwtUtil jwtTokenUtil) {
    this.authenticationManager = authenticationManager;
    this.nutzerService = nutzerService;
    this.jwtTokenUtil = jwtTokenUtil;
  }


  /**
   * Checks login data and creates jwt token.
   * @param anfrage Request Object containing Password and Username.
   * @return Returns new Nutzer Object without Password.
   * @throws Exception Thrown when Nutzer data is wrong.
   */
  @PostMapping(path = "/login")
  public ResponseEntity<?> createAuthenticationToken(
          @RequestBody AuthentifizierungAnfrage anfrage) throws Exception {
    Authentication authenticate;

    try {
      Authentication authentication = new UsernamePasswordAuthenticationToken(
              anfrage.getUsername(),
              anfrage.getPassword()
      );

      authenticate = authenticationManager.authenticate(authentication);

    } catch (BadCredentialsException e) {
      throw new Exception("Ungültiger Name oder Passwort");
    }

    final String jwt = jwtTokenUtil.createToken(authenticate);
    Nutzer nutzer = nutzerService.findNutzerByEmail(anfrage.getUsername());

    return ResponseEntity.ok(new AuthentifizierungAntwort(jwt, nutzer.getUsername(),
            nutzer.getAccountname(), nutzer.getVorname(), nutzer.getNachname(),
            nutzer.getNutzerRolle().toString(), nutzer.getId()));
  }
}
