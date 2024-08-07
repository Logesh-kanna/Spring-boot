package com.project.banking.config.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.project.banking.config.service.UserDetailImpl;
import com.project.banking.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${security.jwt.secret-Key}")
	private String secretKey;

	public String generateToken(Authentication authentication) {
		UserDetailImpl userPrincipal = (UserDetailImpl) authentication.getPrincipal();
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userPrincipal.getUsername());
	}

	public String createToken(Map<String, Object> claims, String email) {
		return Jwts.builder().claims(claims).subject(email).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
				.signWith(getKeys()).compact();
	}
	
	public SecretKey getKeys() {
		byte[] key = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(key);
	}
	
	public Claims extractAllClaims(String token) {
		byte[] skey = Decoders.BASE64.decode(secretKey);
		SecretKey key =  Keys.hmacShaKeyFor(skey);
		return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
	}
	
	public <T> T extractClaims(String token, Function<Claims, T> ClaimsReslover) {
		Claims claims = extractAllClaims(token);
		return ClaimsReslover.apply(claims);
	}
	
	public String extractUserName(String token) {
		return extractClaims(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaims(token, Claims::getExpiration);
	}
	
	public Boolean isTokenValid(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Boolean validateToken(String token, String email) {
		String username = extractUserName(token);
		return (username.equals(email)) && !isTokenValid(token);
	}

}
