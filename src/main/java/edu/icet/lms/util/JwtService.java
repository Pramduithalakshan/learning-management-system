package edu.icet.lms.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@SuppressWarnings("unused")
public class JwtService {
    @Value("${SECRET_KEY}")
    private String secretary;
    public String extractUsername(String token){
        final Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }
    private String createToken(Map<String,Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSignInKey())
                .compact();
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secretary);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public boolean isTokenExpired(String token){
        return extraClaims(token,Claims::getExpiration).before(new Date());
    }
    private <T> T extraClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claims = Jwts.parserBuilder().
                setSigningKey(getSignInKey()).
                build().
                parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }
}
