// // package com.example.demo.security;


// // import org.springframework.beans.factory.annotation.Value; // Import Value
// // import org.springframework.stereotype.Component;

// // import io.jsonwebtoken.Claims;
// // import io.jsonwebtoken.Jwts;
// // import io.jsonwebtoken.SignatureAlgorithm;
// // import io.jsonwebtoken.security.Keys;

// // import java.security.Key;
// // import java.util.Date;

// // @Component
// // public class JwtUtil {

// //     // Load secret key from application.properties or application.yml
// //     @Value("${jwt.secret:your-super-secret-key-which-is-at-least-32-characters}") // Default value for local dev
// //     private String secretKeyString;

// //     // This ensures the key is generated once and used consistently
// //     private final Key signingKey;

// //     public JwtUtil(@Value("${jwt.secret:your-super-secret-key-which-is-at-least-32-characters}") String secretKeyString) {
// //         // Ensure the secret key is long enough for HS256 (256 bits = 32 bytes)
// //         // If your key is shorter, you'll get an error.
// //         // It's recommended to generate a key using KeyGenerator class or similar.
// //         if (secretKeyString.length() < 32) {
// //             throw new IllegalArgumentException("JWT secret key must be at least 32 characters long for HS256.");
// //         }
// //         this.signingKey = Keys.hmacShaKeyFor(secretKeyString.getBytes());
// //     }

// //     public String generateToken(String username) {
// //         return Jwts.builder()
// //                 .setSubject(username)
// //                 .setIssuedAt(new Date(System.currentTimeMillis()))
// //                 .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
// //                 .signWith(signingKey, SignatureAlgorithm.HS256)
// //                 .compact();
// //     }
// //     public String extractUsername(String token) {
// //         return Jwts.parser()
// //                 .setSigningKey(signingKey)
// //                 .parseClaimsJws(token)
// //                 .getBody()
// //                 .getSubject();
// //     }
// //     public Claims extractAllClaims(String token) {
// //         return Jwts.parserBuilder()
// //                 .setSigningKey(signingKey)
// //                 .build()
// //                 .parseClaimsJws(token)
// //                 .getBody();
// //     }


// // }



package com.example.demo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    // The secret key is loaded via the constructor using @Value
    private final Key signingKey;

    public JwtUtil(@Value("${jwt.secret:your-super-secret-key-which-is-at-least-32-characters}") String secretKeyString) {
        if (secretKeyString.length() < 32) {
            throw new IllegalArgumentException("JWT secret key must be at least 32 characters long for HS256.");
        }
        this.signingKey = Keys.hmacShaKeyFor(secretKeyString.getBytes());
    }

    /**
     * Generates a JWT token with the username and role.
     * @param username The subject of the token.
     * @param role The role of the user (e.g., "ADMIN", "USER").
     * @return The generated JWT as a String.
     */
    public String generateToken(String username, String role) {
        // MAJOR CHANGE: This method now accepts a 'role' parameter to include in the token.
        // It creates a claims map and adds the role, which is essential for Spring Security's
        // role-based authorization to work correctly.
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
//  claims.put("role", "ROLE_" + role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        // MODIFIED: This method now uses a new, generic helper method to extract the username claim.
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the user's role from the JWT.
     * @param token The JWT string.
     * @return The role string (e.g., "ADMIN").
     */
    // MAJOR CHANGE: This is a brand new method added to retrieve the role from the token's payload.
    // It is used by the JwtAuthenticationFilter to set the user's authorities.
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }
    
    /**
     * A generic helper method to extract any single claim from the token.
     * @param token The JWT string.
     * @param claimsResolver The function to apply to the claims (e.g., Claims::getSubject).
     * @param <T> The type of the claim to be returned.
     * @return The extracted claim.
     */
    // MAJOR CHANGE: This is a new helper method to make claim extraction more reusable and cleaner.
    // It consolidates the logic for getting claims.
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
//use this ...




