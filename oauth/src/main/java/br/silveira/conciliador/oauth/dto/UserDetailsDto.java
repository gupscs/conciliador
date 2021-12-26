package br.silveira.conciliador.oauth.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.silveira.conciliador.common.enums.RoleEnum;
import lombok.Data;

@Data
public class UserDetailsDto implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	private String username;

	private String password;

	private String companyId;

	private String email;

	private Date insertDate;

	private String insertId;

	private Date updateDate;

	private String updateId;

	private Boolean enable;

	private Boolean blocked;

	private Boolean lastPasswordChangeDate;

	private Boolean needPasswordChange;

	private List<RoleEnum> roles;
	
	private Collection<? extends GrantedAuthority> grants;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(grants == null) {
			if (getRoles() == null) {
				return null;
			} else {
				grants = getRoles().stream().map(r -> new SimpleGrantedAuthority(r.toString())).collect(Collectors.toList());
			}
		}
		return grants;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonLocked();
	}

	@Override
	public boolean isAccountNonLocked() {
		return getBlocked() != null ? !getBlocked() : true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isEnabled();
	}

	@Override
	public boolean isEnabled() {
		return getEnable() != null ? getEnable() : true;
	}

}
