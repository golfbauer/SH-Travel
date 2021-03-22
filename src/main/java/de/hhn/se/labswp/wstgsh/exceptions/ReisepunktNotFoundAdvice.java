package de.hhn.se.labswp.wstgsh.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ReisepunktNotFoundAdvice {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);

  /**
   * AdviceClass which return error Message for Controller Reisepunkt.
   * @param exception ExceptionHandler created to throw Exception
   *                 in case requested ID doesnt exist for Reisepunkt.
   * @return Same error Message as exception.
   */
  @ResponseBody
  @ExceptionHandler(ReisepunktNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String reisepunkNotFoundHandler(ReisepunktNotFoundException exception) {
    return exception.getMessage();
  }
}
