package com.javagang.rdcoursemanagementplatform.utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;
import java.util.Date;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

@Component
public class JwtUtility {

  @Value("${jwt.secret}")
  private String secret;

  public Claims extractClaims(String token) throws JwtException {
    try {
      return Jwts.parser()
          .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
          .parseClaimsJws(token)
          .getBody();
    } catch (Exception e) {
      throw new JwtException("Invalid token: failed to extract claims");
    }
  }

  public String generateToken(Map<String, Object> claims, String mail, long validity) {
    var nowMillis = System.currentTimeMillis();
    var now = new Date(nowMillis);
    return Jwts.builder()
        .setSubject(mail)
        .setIssuedAt(now)
        .setExpiration(new Date(nowMillis + validity))
        .setClaims(claims)
        .signWith(
            SignatureAlgorithm.HS256,
            Base64.getEncoder().encodeToString(secret.getBytes(StandardCharsets.UTF_8)))
        .compact();
  }
}
