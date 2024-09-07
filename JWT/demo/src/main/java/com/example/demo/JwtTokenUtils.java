package com.example.demo;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.services.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenUtils {
    
    private static final String secretKey = "ogakey123456789ABCDEFGogakey123456789ABCDEFG";
    private static final long JWT_TOKEN_VALIDITY = 60 * 10;

    private final UserService userService;

    public Boolean validateToken(String token, UserDetails userDetails) {

        String userName = getUserNameFromToken(token);
        boolean isTokenExpired = isTokenExpired(token);

        return userName.equals(userDetails.getUsername()) && !isTokenExpired;
    }

    public boolean isTokenExpired(String token){
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String getUserNameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimGetter){
        Claims claims = getAllClaimsFromToken(token);
        return claimGetter.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        String subject = userDetails.getUsername();

        return Jwts.builder()
                   .setClaims(claims)
                   .setSubject(subject)
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                   .signWith(SignatureAlgorithm.HS256, secretKey)
                   .compact();
    }
}
