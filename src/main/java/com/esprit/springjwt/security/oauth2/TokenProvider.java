package com.esprit.springjwt.security.oauth2;

import com.esprit.springjwt.config.AppProperties;
import com.esprit.springjwt.security.services.UserPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private AppProperties appProperties;

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        logger.info("Creating token for user: " + userPrincipal.getUsername() + " with ID: " + userPrincipal.getId());

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();
    }


    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature: ", ex);
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token: ", ex);
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token: ", ex);
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token: ", ex);
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty: ", ex);
        }
        return false;
    }
}