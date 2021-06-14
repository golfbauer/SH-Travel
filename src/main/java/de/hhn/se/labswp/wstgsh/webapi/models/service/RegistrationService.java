package de.hhn.se.labswp.wstgsh.webapi.models.service;

import de.hhn.se.labswp.wstgsh.webapi.models.email.EmailSender;
import de.hhn.se.labswp.wstgsh.webapi.models.email.EmailValidator;
import de.hhn.se.labswp.wstgsh.webapi.models.nutzer.Nutzer;
import de.hhn.se.labswp.wstgsh.webapi.models.nutzer.NutzerRepository;
import de.hhn.se.labswp.wstgsh.webapi.models.nutzer.NutzerRolle;
import de.hhn.se.labswp.wstgsh.webapi.models.registration.RegistrationAnfrage;
import de.hhn.se.labswp.wstgsh.webapi.models.token.BestaetigungsToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegistrationService {

  private final NutzerService nutzerService;
  private final EmailValidator emailValidator;
  private final BestaetigungsTokenService bestaetigungsTokenService;
  private final EmailSender emailSender;

  /**
   * Constructor for Services.
   * @param nutzerService For Nutzer.
   * @param emailValidator To check Email Validation.
   * @param bestaetigungsTokenService Token for email validation.
   * @param emailSender Sends email.
   */
  public RegistrationService(NutzerService nutzerService, EmailValidator emailValidator,
                             BestaetigungsTokenService bestaetigungsTokenService,
                             EmailSender emailSender) {
    this.nutzerService = nutzerService;
    this.emailValidator = emailValidator;
    this.bestaetigungsTokenService = bestaetigungsTokenService;
    this.emailSender = emailSender;
  }

  /**
   * Logic for registering a Nutzer.
   * @param anfrage Contains all Nutzer information.
   * @return Token.
   */
  public String register(RegistrationAnfrage anfrage) {
    boolean valideEmail = emailValidator.test(anfrage.getEmail());

    if (!valideEmail) {
      throw new IllegalStateException("Email nicht valide!");
    }
    // TODO: Nutzerrolle muss entweder in Objekt oder seperat übergeben werden, vorerst hardcoded
    String token = nutzerService.signUpNutzer(
            new Nutzer(
                    anfrage.getVorname(),
                    anfrage.getNachname(),
                    anfrage.getEmail(),
                    anfrage.getAccountname(),
                    anfrage.getPasswort(),
                    NutzerRolle.Kunde
            )
    );
    String link = "http://localhost:8080/register/confirm?token=" + token;

    emailSender.sendMailViaGmail(anfrage.getEmail(), baueEmail(link, anfrage.getVorname()));
    return token;
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
                    new IllegalStateException("Token nicht gefunden!"));

    if (bestaetigungsToken.getBeastaetigtUm() != null) {
      throw new IllegalStateException("Email wurde bereits bestätigt!");
    }

    LocalDateTime verfaelltUm = bestaetigungsToken.getVerfaelltUm();

    if (verfaelltUm.isBefore(LocalDateTime.now())) {
      throw new IllegalStateException("Token ist bereits abgelaufen!");
    }

    bestaetigungsTokenService.setConfirmedAt(token);
    nutzerService.enableNutzer(bestaetigungsToken.getNutzer().getEmail());
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
