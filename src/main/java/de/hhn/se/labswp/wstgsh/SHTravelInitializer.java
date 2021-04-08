package de.hhn.se.labswp.wstgsh;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class SHTravelInitializer extends SpringBootServletInitializer {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SHTravelInitializer.class);

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(SHTravelApplication.class);
  }
}
