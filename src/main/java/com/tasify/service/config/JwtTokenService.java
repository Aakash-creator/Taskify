package com.tasify.service.config;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenService 
{

	private String SecretKey ;
	
	public JwtTokenService()
	{
		this.SecretKey=getTheSecretKey();
	}
	
	private String getTheSecretKey()
	{
		try {
			KeyGenerator key = KeyGenerator.getInstance("HmacSHA256");
			SecretKey secret = key.generateKey();
			return Base64.getEncoder().encodeToString(secret.getEncoded());
		} catch (Exception e) {
			throw new RuntimeException("Error is Generating Key");
		}
	}

	public String generateToken(String name) {
		
		Map<String, Object> claims = new HashMap<>();
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(name)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+180000000))
				.signWith(getKey(), SignatureAlgorithm.HS256).compact();
				
	}

	private Key getKey() {

		byte[] b = Decoders.BASE64.decode(SecretKey);
		return Keys.hmacShaKeyFor(b);
	}

	

	public String extractUserName(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token , Claims::getSubject);
	}
	
	private <T> T extractClaims(String token ,Function<Claims, T>  claimResolverFunction) 
	{
		System.out.println("Token in extract claim " + token);
		final Claims claims = extractAllClaims(token);
		System.out.println("Claims" + claims);
		return claimResolverFunction.apply(claims);
		
	}

	private Claims extractAllClaims(String token) {
			System.out.println("token has extracted all claims "+ token);
			
			return Jwts.parserBuilder()
					.setSigningKey(getKey())
					.build()
					.parseClaimsJws(token)
					.getBody();
			
		
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUserName(token);
		
		return (username.equals(userDetails.getUsername()) && !isTokenExpried(token));
		
	}

	private boolean isTokenExpried(String token) {
		System.out.println("Token is exrired");
		return extractExpiration(token).before(new Date()); 
		
	}

	private Date extractExpiration(String token) {
		return extractClaims(token, Claims::getExpiration);
	}
	

}
