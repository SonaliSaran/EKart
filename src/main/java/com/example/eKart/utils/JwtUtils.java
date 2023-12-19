package com.example.eKart.utils;


import com.example.eKart.dto.SignUpDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    private static String secret = "secretkey";

    long millisecond = System.currentTimeMillis();
    long expiryTime = millisecond + (60 * 60) * 1000; // 60*60 represents 1 hour and 1000 is for cobersion of millisecond to seconds
    Date issueAt = new Date(millisecond);
    Date expiryAt = new Date(expiryTime);

    Set<String> revolvedToken = new HashSet<>();

    public String generateJWT(SignUpDto signUpDto) {
        Claims claims = Jwts.claims().setIssuer(signUpDto.getEmail())
                .setIssuedAt(issueAt).setExpiration(expiryAt);


        // these are the optional claims .You can add this when u think its needed
        claims.put("phoneNumber", signUpDto.getPhoneNumber());


        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getIssuer();
    }

    public boolean isTokenExpired(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        Date expirationDate = claims.getExpiration();
        return expirationDate.before(new Date());
    }

    public boolean tokenAlreadyExist(String token){
        if(!revolvedToken.contains(token)){
            return true;
        }
        else
            return false;
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;    }
        catch (Exception e) {        // Invalid or expired token
            return false;    }}

    public void invalidatingToken(String token) {
        revolvedToken.add(token);
    }




}
