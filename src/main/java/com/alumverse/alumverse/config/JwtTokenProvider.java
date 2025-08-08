package com.alumverse.alumverse.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // These values should be placed in your application.properties file for better security and management.
    // Example:
    // app.jwt-secret=YourSuperLongAndSecretKeyThatIsAtLeast256BitsLong
    // app.jwt-expiration-milliseconds=604800000
    @Value("${app.jwt-secret:DefaultSecretKeyForAlumverseAppThatIsVeryLongAndSecure}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds:604800000}") // Default is 7 days
    private int jwtExpirationInMs;

    private Key key;

    /**
     * This method is called after the bean has been constructed.
     * It initializes the signing key from the jwtSecret string.
     */
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    /**
     * Generates a JWT for a given authenticated user.
     *
     * @param authentication The user's authentication object from Spring Security.
     * @return A signed JWT string.
     */
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Extracts the username (email) from a JWT.
     *
     * @param token The JWT string.
     * @return The username (email) contained in the token.
     */
    public String getUsername(String token) {
        // Using the older, more compatible parser() method
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    /**
     * Validates a JWT.
     *
     * @param token The JWT string to validate.
     * @return true if the token is valid, false otherwise.
     */
    public boolean validateToken(String token) {
        try {
            // Using the older, more compatible parser() method
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            // You can log different exceptions here for debugging purposes
            // e.g., MalformedJwtException, ExpiredJwtException, UnsupportedJwtException, IllegalArgumentException
            return false;
        }
    }
}
