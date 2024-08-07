package com.project.e_commerce.Configure.Jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.project.e_commerce.Configure.Jwt.Service.UserDetailImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final String secretKey = "iCJgUhRZFxyw1l6ZIkAI1ESApj3d6QyYQFqEAatkRxgvqD5kIHUKw+OQaiyp778Y";

	public String generateToken(Authentication authentication) {
		UserDetailImpl userPrincipal = (UserDetailImpl) authentication.getPrincipal();
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userPrincipal.getUsername());
	}

	public String createToken(Map<String, Object> claims, String email) {
		return Jwts.builder().claims(claims).subject(email).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)).signWith(getKey()).compact();
	}

	private SecretKey getKey() {
		byte[] key = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(key);
	}

	public Claims extractAllClaims(String token) {
		byte[] secretkey = Decoders.BASE64.decode(secretKey);
		SecretKey key = Keys.hmacShaKeyFor(secretkey);
		return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
	}

	public <T> T extractClaims(String token, Function<Claims, T> ClaimsResolver) {
		final Claims claims = extractAllClaims(token);
		return ClaimsResolver.apply(claims);
	}

	public String extractUserName(String token) {
		return extractClaims(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaims(token, Claims::getExpiration);
	}

	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public Boolean validationToken(String token) {
		final String tokenUsername = extractUserName(token);
		return (tokenUsername.equals(tokenUsername) && !isTokenExpired(token));
	}
}
