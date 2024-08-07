package com.spring.Uben.User.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;

import com.spring.Uben.User.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class jwtService {

	private String secretKey = "quKtncsaaLPP3jDzs4OSeHj3pUIrTXDMMfW/90Wt9EVayin1icST7oFrz2rhhtMU";

	 public String generateToken(UserModel user) {
	        Map<String, Object> claims = new HashMap<>();
	        return createToken(claims, user);
	    }
	
	public String createToken(Map<String, Object> claims, UserModel user) {
		return Jwts.builder().claims(claims).subject(user.getName()).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)).signWith(getSignKey()).compact();
	}

	private Key getSignKey() {
		byte[] keyByte = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyByte);
	}

	private Claims extractAllClaims(String token) {
		byte[] keyByte = Decoders.BASE64.decode(secretKey);
		SecretKey key = Keys.hmacShaKeyFor(keyByte);
		return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Boolean validateToken(String token, UserModel userDetails) {
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(userDetails.getName()) && !isTokenExpired(token));
    }
}
