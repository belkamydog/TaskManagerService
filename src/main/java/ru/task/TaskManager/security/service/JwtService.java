package ru.task.TaskManager.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import ru.task.TaskManager.models.User;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    public String generateToken(User user) {
        return Jwts.builder()
                .setClaims(createClaims(user))
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSigningKey())
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Claims createClaims(User user) {
        Claims claims = Jwts.claims();
        claims.put("id", (user.getId()));
        claims.put("email", user.getEmail());
        return claims;
    }

    private Key getSigningKey() {
        final String secretKey = "5%2Nnf/-+.gf_=Cws@saxzcdam.,.*dfg~`v^*gcv(%%%gbf";
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}