package de.hhn.se.labswp.wstgsh.security.jwt;

import com.google.common.base.Strings;
import de.hhn.se.labswp.wstgsh.api.service.NutzerService;
import de.hhn.se.labswp.wstgsh.security.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private JwtUtil jwtUtil;
  private JwtConfig jwtConfig;

  public JwtRequestFilter(JwtUtil jwtUtil, JwtConfig jwtConfig) {
    this.jwtUtil = jwtUtil;
    this.jwtConfig = jwtConfig;
  }


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {

    final String authorizationHeader = request.getHeader("Authorization");

    if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader
            .startsWith(jwtConfig.getTokenPrefix())) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = authorizationHeader.substring(7);

    try {
      Claims body = jwtUtil.extractAllClaims(token);

      String username = jwtUtil.extractUsername(token);


      List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authority");
      Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
              .map(m -> new SimpleGrantedAuthority(m.get("authority")))
              .collect(Collectors.toSet());

      Authentication authentication = new UsernamePasswordAuthenticationToken(
              username,
              null,
              simpleGrantedAuthorities
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);

    } catch (JwtException e) {
      throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
    }

    filterChain.doFilter(request, response);
  }
}
