package br.silveira.conciliador.common.security.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil implements Serializable{
	
	private static final Logger log = LogManager.getLogger(JwtTokenUtil.class);

	private static final long serialVersionUID = 1L;
	
	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_CREATED = "created";
	static final String CLAIM_KEY_EXPIRED = "exp";
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	
	public String getUsernameFromToken(String token) {
		String username = null;
		try {
			final Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		}catch(Exception e) {
			log.error("error to read username from token", e);
		}
		return username;
	}
	
	
	public Date getExpirationDateFromToken(String token) {
		Date expiration = null;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		}catch(Exception e) {
			log.error("error to read expiration date from token", e);
		}
		return expiration;
		
	}
	
	private Claims getClaimsFromToken(String token) {
		Claims claims = null;
		try {
			 claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		}catch(Exception e) {
			log.error("error to read claims from token", e);
		}
		return claims;
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		final Date createDate = new Date();
		claims.put(CLAIM_KEY_CREATED, createDate);
		return doGenerateToken(claims);
	}


	private String doGenerateToken(Map<String, Object> claims) {
		final Date createdDate = (Date) claims.get(CLAIM_KEY_CREATED);
		final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);
		return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	public Boolean canTokenBeRefreshed(String token) {
		return !isTokenExpired(token);
	}
	
	public String refreshToken(String token) {
		String refreshToken = null;
		try {
			final Claims claims = getClaimsFromToken(token);
			final Date createDate = new Date();
			claims.put(CLAIM_KEY_CREATED, createDate);
			refreshToken = doGenerateToken(claims);
		}catch(Exception e) {
			log.error("error to refresh token", e);
		}
		return refreshToken;
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		JwtUser user = (JwtUser) userDetails;
		final String username = getUsernameFromToken(token);
		return ( username.equals(user.getUsername()) && canTokenBeRefreshed(token));
	}
	
	
}
