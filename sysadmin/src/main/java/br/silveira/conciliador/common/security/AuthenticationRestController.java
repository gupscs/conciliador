package br.silveira.conciliador.common.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.silveira.conciliador.common.security.jwt.CurrentUser;
import br.silveira.conciliador.common.security.jwt.JwtAuthenticationRequest;
import br.silveira.conciliador.common.security.jwt.JwtTokenUtil;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationRestController {
	
	private static final String AUTHORIZATION = "Authorization";
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@PostMapping("/api/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) {
		final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new CurrentUser(authenticationRequest.getUsername(), token));		
	}
	
	@PostMapping("/api/refresh")
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request){
		String token = request.getHeader(AUTHORIZATION);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		
		if(jwtTokenUtil.canTokenBeRefreshed(token)) {
			String refreshToken = jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new CurrentUser(username, refreshToken));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
		
	}
	
}
