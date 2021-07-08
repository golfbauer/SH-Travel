package de.hhn.se.labswp.wstgsh.security.config;

import de.hhn.se.labswp.wstgsh.api.service.NutzerService;
import de.hhn.se.labswp.wstgsh.security.jwt.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final NutzerService nutzerService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private JwtRequestFilter jwtRequestFilter;

  public WebSecurityConfig(NutzerService nutzerService,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           JwtRequestFilter jwtRequestFilter) {
    this.nutzerService = nutzerService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.jwtRequestFilter = jwtRequestFilter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(
                    "/", "/register", "/login", "/attraktion/oeffentlich",
                    "/punkt/oeffentlich", "/reise/oeffentlich", "/reisepunkt/oeffentlich",
                    "/sehenswuerdigkeit/oeffentlich", "/register/**", "/css/**", "/js/**",
                    "/favicon.ico", "/img/**"
            ).permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
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
