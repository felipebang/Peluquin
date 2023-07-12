package com.indra.cmoff.auth.payload;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.indra.cmoff.service.impl.UserDetailsImpl;
import com.indra.cmoff.service.impl.UsuarioServiceImpl;
import com.indra.cmoff.dto.PersonaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
	private String accessToken;
	private Long id;
	private PersonaDTO persona;
	private String username;
	private Collection<? extends GrantedAuthority> roles;
	
	public JwtResponse(String accessToken, String username, 
			Collection<? extends GrantedAuthority> roles) {
		super();
		this.accessToken = accessToken;
		this.username = username;
		this.roles = roles;
	}
	
	public static JwtResponse buildSuperUser(String accessToken, User userDetails) {		
		return new JwtResponse(accessToken, userDetails.getUsername(), userDetails.getAuthorities());
	}
	
	public static JwtResponse buildUserDetails(String accessToken, UserDetailsImpl userDetails) {		
		return new JwtResponse(accessToken, userDetails.getId(), userDetails.getPersona(),
				userDetails.getUsername(), userDetails.getAuthorities());
	}

}
