package kz.com.ars.message_system.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;
import javax.crypto.SecretKey;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil implements Serializable {
    @Value("${dev.security.jwt.secret-key}")
    private String secret;

    @Value("${dev.security.jwt.refresh-token-expiration}")
    private long jwtRefreshValidity;

    @Value("${dev.security.jwt.access-token-expiration}")
    private long jwtAccessTokenValidity;

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateAccessToken(UserDetails userDetails) {
        Map<String , Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());
        return doGenerateToken(claims, userDetails.getUsername(), jwtAccessTokenValidity);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername(), jwtRefreshValidity);
    }

    public Boolean validateToken(String token) {
        final String username = getUsernameFromToken(token);
        return (username != null && !isTokenExpired(token));
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private  <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }



    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    private String doGenerateToken(Map<String, Object> claims, String subject, long validity) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + validity);

        return io.jsonwebtoken.Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(getSignInKey())
                .compact();
    }

}
