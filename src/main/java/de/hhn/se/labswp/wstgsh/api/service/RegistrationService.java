package de.hhn.se.labswp.wstgsh.api.service;

import de.hhn.se.labswp.wstgsh.api.models.Reisekatalog;
import de.hhn.se.labswp.wstgsh.api.models.ReisekatalogRepository;
import de.hhn.se.labswp.wstgsh.api.webcontroller.ReisekatalogController;
import de.hhn.se.labswp.wstgsh.security.email.EmailSender;
import de.hhn.se.labswp.wstgsh.security.email.EmailValidator;
import de.hhn.se.labswp.wstgsh.api.models.Nutzer;
import de.hhn.se.labswp.wstgsh.security.registration.RegistrationAnfrage;
import de.hhn.se.labswp.wstgsh.security.registration.RegistrationAntwort;
import de.hhn.se.labswp.wstgsh.security.token.BestaetigungsToken;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

  private final NutzerService nutzerService;
  private final EmailValidator emailValidator;
  private final BestaetigungsTokenService bestaetigungsTokenService;
  private final EmailSender emailSender;
  private final ReisekatalogRepository reisekatalogRepository;

  /**
   * Constructor for Services.
   * @param nutzerService For Nutzer.
   * @param emailValidator To check Email Validation.
   * @param bestaetigungsTokenService Token for email validation.
   * @param emailSender Sends email.
   * @param reisekatalogRepository
   */
  public RegistrationService(NutzerService nutzerService, EmailValidator emailValidator,
                             BestaetigungsTokenService bestaetigungsTokenService,
                             EmailSender emailSender,
                             ReisekatalogRepository reisekatalogRepository) {
    this.nutzerService = nutzerService;
    this.emailValidator = emailValidator;
    this.bestaetigungsTokenService = bestaetigungsTokenService;
    this.emailSender = emailSender;
    this.reisekatalogRepository = reisekatalogRepository;
  }

  /**
   * Logic for registering a Nutzer.
   * @param anfrage Contains all Nutzer information.
   * @return Token.
   */
  public RegistrationAntwort register(RegistrationAnfrage anfrage) {
    boolean valideEmail = emailValidator.test(anfrage.getEmail());

    if (!valideEmail) {
      throw new IllegalStateException("Email nicht valide!");
    }
    RegistrationAntwort antwort = nutzerService.signUpNutzer(
            new Nutzer(
                    anfrage.getVorname(),
                    anfrage.getNachname(),
                    anfrage.getEmail(),
                    anfrage.getAccountname(),
                    anfrage.getPasswort(),
                    anfrage.getNutzerRolle()
            )
    );
    String link = "http://localhost:8080/register/confirm?token=" + antwort.getToken();
    sendEmail(anfrage.getEmail(), link, anfrage.getVorname());

    confirmToken(antwort.getToken());
    return antwort;
  }

  /**
   * Resending Token for Nutzer.
   * @param email Email of Nutzer.
   * @return Answer containing Token and String answer.
   */
  public RegistrationAntwort resendToken(String email) {
    RegistrationAntwort antwort = nutzerService.resendToken(email);

    String link = "http://localhost:8080/register/confirm?token=" + antwort.getToken();

    sendEmail(email, link, "Nutzer");
    return antwort;
  }

  public void sendEmail(String email, String link, String vorname) {
    emailSender.sendMailViaGmail(email, baueEmail(link, vorname));
  }

  /**
   * Confirm that Nutzer is owner of email.
   * @param token Token from Nutzer.
   * @return Confirmation.
   */
  @Transactional
  public String confirmToken(String token) {
    BestaetigungsToken bestaetigungsToken = bestaetigungsTokenService
            .getToken(token)
            .orElseThrow(() ->
                    new IllegalStateException("Token nicht gefunden! Bitte gehen Sie in die "
                            + "Einstellungen und lassen sie sich erneut eine email senden."));

    if (bestaetigungsToken.getBeastaetigtUm() != null) {
      throw new IllegalStateException("Email wurde bereits best??tigt!");
    }

    LocalDateTime verfaelltUm = bestaetigungsToken.getVerfaelltUm();

    if (verfaelltUm.isBefore(LocalDateTime.now())) {
      throw new IllegalStateException("Token ist bereits abgelaufen!");
    }

    bestaetigungsTokenService.setConfirmedAt(token);
    nutzerService.enableNutzer(bestaetigungsToken.getNutzer().getEmail());
    reisekatalogRepository.save(new Reisekatalog(bestaetigungsToken.getNutzer()));
    return "confirmed";
  }

  private String baueEmail(String link, String nutzer) {
    return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n"
            + "\n"
            + "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n"
            + "\n"
            + "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
            + "    <tbody><tr>\n"
            + "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n"
            + "        \n"
            + "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n"
            + "          <tbody><tr>\n"
            + "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n"
            + "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
            + "                  <tbody><tr>\n"
            + "                    <td style=\"padding-left:10px\">\n"
            + "                  \n"
            + "                    </td>\n"
            + "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n"
            + "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n"
            + "                    </td>\n"
            + "                  </tr>\n"
            + "                </tbody></table>\n"
            + "              </a>\n"
            + "            </td>\n"
            + "          </tr>\n"
            + "        </tbody></table>\n"
            + "        \n"
            + "      </td>\n"
            + "    </tr>\n"
            + "  </tbody></table>\n"
            + "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
            + "    <tbody><tr>\n"
            + "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n"
            + "      <td>\n"
            + "        \n"
            + "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
            + "                  <tbody><tr>\n"
            + "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n"
            + "                  </tr>\n"
            + "                </tbody></table>\n"
            + "        \n"
            + "      </td>\n"
            + "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n"
            + "    </tr>\n"
            + "  </tbody></table>\n"
            + "\n"
            + "\n"
            + "\n"
            + "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
            + "    <tbody><tr>\n"
            + "      <td height=\"30\"><br></td>\n"
            + "    </tr>\n"
            + "    <tr>\n"
            + "      <td width=\"10\" valign=\"middle\"><br></td>\n"
            + "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n"
            + "        \n"
            + "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;"
            + "color:#0b0c0c\">Hi " + nutzer + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;"
            + "line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the "
            + "link below to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon!</p>"
            + "        \n"
            + "      </td>\n"
            + "      <td width=\"10\" valign=\"middle\"><br></td>\n"
            + "    </tr>\n"
            + "    <tr>\n"
            + "      <td height=\"30\"><br></td>\n"
            + "    </tr>\n"
            + "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n"
            + "\n"
            + "</div></div>";
  }
}
