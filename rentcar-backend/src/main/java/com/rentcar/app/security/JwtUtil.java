package com.rentcar.app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Utility class for JSON Web Token (JWT) operations.
 * This class provides methods for generating, extracting claims from, and validating JWTs.
 */
@Component
public class JwtUtil {

    /**
     * The secret key used for signing and verifying JWTs, loaded from application properties.
     */
    @Value("${jwt.secret}")
    private String secret;

    private SecretKey SECRET_KEY;

    /**
     * Initializes the SECRET_KEY after the secret string is injected.
     * The secret string is base64 decoded to create the SecretKey.
     */
    @PostConstruct
    public void init() {
        this.SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    /**
     * Extracts the username (subject) from a JWT.
     * @param token The JWT string.
     * @return The username extracted from the token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration date from a JWT.
     * @param token The JWT string.
     * @return The expiration date extracted from the token.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts a specific claim from a JWT using a claims resolver function.
     * @param token The JWT string.
     * @param claimsResolver A function to resolve the desired claim from the {@link Claims} object.
     * @param <T> The type of the claim to extract.
     * @return The extracted claim.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from a JWT.
     * This method also handles the validation of the token's signature.
     * @param token The JWT string.
     * @return The {@link Claims} object containing all claims.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }

    /**
     * Checks if a JWT is expired.
     * @param token The JWT string.
     * @return True if the token is expired, false otherwise.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Generates a JWT for a given user.
     * @param userDetails The {@link UserDetails} of the user for whom the token is to be generated.
     * @return The generated JWT string.
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Creates the JWT string with specified claims, subject, issue date, and expiration date.
     * @param claims Additional claims to be included in the JWT payload.
     * @param subject The subject of the JWT (typically the username).
     * @return The compact JWT string.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours validity
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256).compact();
    }

    /**
     * Validates a JWT against user details.
     * Checks if the username in the token matches the user details and if the token is not expired.
     * @param token The JWT string.
     * @param userDetails The {@link UserDetails} of the user to validate against.
     * @return True if the token is valid, false otherwise.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
