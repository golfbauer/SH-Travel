package de.hhn.se.labswp.wstgsh.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

  private final String SECRET_KEY =
          "secretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecret";

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  /**
   * Used to create token.
   * @param authentication Authentication to extract User Details.
   * @return Token as String.
   */
  public String createToken(Authentication authentication) {
    return Jwts.builder()
            .setSubject(authentication.getName())
            .claim("authority", authentication.getAuthorities())
            .setIssuedAt(new Date())
            .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(2)))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
  }
}
