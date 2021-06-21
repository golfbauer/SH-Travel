package de.hhn.se.labswp.wstgsh.webapi.models.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hhn.se.labswp.wstgsh.webapi.models.nutzer.Nutzer;
import de.hhn.se.labswp.wstgsh.webapi.models.service.NutzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static de.hhn.se.labswp.wstgsh.webapi.models.nutzer.NutzerRolle.ADMIN;
import static de.hhn.se.labswp.wstgsh.webapi.models.nutzer.NutzerRolle.ANBIETER;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
            .antMatchers("/", "/register").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
              .loginPage("/login") //the URL on which the clients should post the login information
              .permitAll()
              .defaultSuccessUrl("/", true)
              .usernameParameter("username") //the username parameter in the queryString, default is 'username'
              .passwordParameter("password") //the password parameter in the queryString, default is 'password'
              .successHandler((request, response, authentication) -> {
                Collection roles = authentication.getAuthorities();
                String role = roles.iterator().next().toString();
                response.getWriter().write(role);
                response.getWriter().flush();
                response.setStatus(200);
              })
            .and()
            .logout()
              .logoutUrl("/logout")          //the URL on which the clients should post if they want to logout
              .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // Sets request
              // method for logout -> If csrf is enabled post is required
              .clearAuthentication(true)
              .invalidateHttpSession(true)
              .deleteCookies("JSESSONID")
              .logoutSuccessUrl("/");
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
