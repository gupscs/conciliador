package br.silveira.conciliador.common.security.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUser implements UserDetails{
	
	
	private static final long serialVersionUID = 1L;

	private final String id;
	private final String username;
	private final String password;
	private final Collection<? extends GrantedAuthority> authorities;
	
	public JwtUser(String id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
	
	@JsonIgnore
	public String getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	public boolean isAccountNonLocked() {
		return false;
	}

	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	

}
