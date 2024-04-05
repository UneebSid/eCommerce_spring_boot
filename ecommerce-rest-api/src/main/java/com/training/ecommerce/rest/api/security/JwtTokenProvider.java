package com.training.ecommerce.rest.api.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider
{
    private String jwtSeceret = "9f27307408f234f3cabc2536fb4a88fe1417e03cb2ea57b5e0201b6b9470c845";
    private Long jwtExpirationDate = 604800L;

    //generate token
    public String generateToken(Authentication authentication)
    {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
        String token = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(key())
                .compact();
        return token;
    }

    private Key key()
    {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSeceret));
    }

    //get username from jwt secret
    public String getUsername(String token)
    {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    //velidate jwt token

    public boolean validateToken(String token)
    {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException exception)
        {
           throw  new RuntimeException("invalid jwt token!.");

        }catch (ExpiredJwtException exception)
        {
            throw  new RuntimeException("expired jwt token.");

        }catch (UnsupportedJwtException exception)
        {
            throw  new RuntimeException("unsupported jwt token.");

        }catch (IllegalArgumentException e)
        {
            throw  new RuntimeException("jwt claims string is null or empty.");
        }

    }

}
