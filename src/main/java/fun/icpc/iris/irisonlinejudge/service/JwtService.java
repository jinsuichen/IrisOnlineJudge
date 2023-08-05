package fun.icpc.iris.irisonlinejudge.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * The service for jwt.
 */
@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    /**
     * get the sign in key, user's handle in this project
     *
     * @param token the token
     * @return the sign in key
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * get the expiration date
     *
     * @param token          the token
     * @param claimsResolver the claims resolver
     * @param <T>            the type of the claims
     * @return the expiration date
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * generate the token
     *
     * @param userDetails the user details
     * @return the token
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * generate the token
     *
     * @param extraClaims the extra claims
     * @param userDetails the user details
     * @return the token
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    /**
     * get the sign in key
     *
     * @param userDetails the user details
     * @return the sign in key
     */
    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    /**
     * build the token
     *
     * @param extraClaims the extra claims
     * @param userDetails the user details
     * @param expiration  the expiration
     * @return the token
     */
    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * validate the token
     *
     * @param token       the token
     * @param userDetails the user details
     * @return true if the token is valid
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * validate the token
     *
     * @param token the token
     * @return true if the token is valid
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * get the expiration date
     *
     * @param token the token
     * @return the expiration date
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * extract all claims
     *
     * @param token the token
     * @return all claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * get the sign in key
     *
     * @return the sign in key
     */
    private Key getSignInKey() {
        // the secret key is base64 encoded
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        // use HMAC SHA algorithm
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
