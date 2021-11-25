package br.silveira.conciliador.common.security.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.silveira.conciliador.sysadmin.dto.UserDto;

public class JwtUserFactory {
	
	private static final String KEY_COMPANY_ID = "COMPANY_ID";
	
	private JwtUserFactory() {}
	
	public static JwtUser create(UserDto user) {
		return new JwtUser(user.getUserId(), user.getUsername(), user.getPassword(), mapToGrantedAuthorities(user.getCompanyId()));
	}

	private static Collection<? extends GrantedAuthority> mapToGrantedAuthorities(Long companyId) {
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		auths.add(new SimpleGrantedAuthority(KEY_COMPANY_ID+":"+companyId));
		return auths;
	}

}
