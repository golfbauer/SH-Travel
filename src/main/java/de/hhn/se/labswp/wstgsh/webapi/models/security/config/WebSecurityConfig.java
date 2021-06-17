package de.hhn.se.labswp.wstgsh.webapi.models.security.config;

import de.hhn.se.labswp.wstgsh.webapi.models.service.NutzerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final NutzerService nutzerService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public WebSecurityConfig(NutzerService nutzerService,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.nutzerService = nutzerService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "/attraktion",
                    "/sehenswuerdigkeit", "/punkt", "/reise",
                    "/reisepunkt", "/register", "register/confirm",
                    "/reise/reisepunkt/*", "/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  /**
   * I dont even know myself tutorial told me to do that though.
   * @return Provider with set PasswordEncoder and nutzerService.
   */
  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider =
            new DaoAuthenticationProvider();
    provider.setPasswordEncoder(bCryptPasswordEncoder);
    provider.setUserDetailsService(nutzerService);
    return provider;
  }
}
