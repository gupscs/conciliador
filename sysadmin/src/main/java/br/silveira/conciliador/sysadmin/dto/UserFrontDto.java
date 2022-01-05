package br.silveira.conciliador.sysadmin.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UserFrontDto {
	
	private String id;
	private String companyId;
	private String fullName;
	private String username;
	private String avatar;
	private String email;
	private String role;
	private List<Ability> ability;
	
	public void addAbility(String action, String subject) {
		if(ability == null) {
			ability = new ArrayList<Ability>();
		}
		ability.add(new Ability(action, subject));
	}
	
	@Data
	public class Ability{
		
		public Ability() {}
		
		public Ability(String action, String subject) {
			this.action = action;
			this.subject = subject;
		}
		private String action;
		private String subject;
	}
	

}
