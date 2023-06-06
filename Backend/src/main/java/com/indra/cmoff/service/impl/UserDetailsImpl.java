package com.indra.cmoff.service.impl;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.indra.cmoff.dto.PersonaDTO;
import com.indra.cmoff.dto.UsuarioDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails{
	
	/** Serial Version */
	private static final long serialVersionUID = 4286699538973067464L;
	
	private Long id;
	private PersonaDTO persona;
	private String username;
	@JsonIgnore
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private boolean isEnabled;

	public UserDetailsImpl(String username, String password,
			Collection<? extends GrantedAuthority> authorities, boolean isEnabled) {
		super();
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.isEnabled = isEnabled;
	}

	public static UserDetailsImpl buildSuperUser(String username, String password,
			Collection<? extends GrantedAuthority> authorities, boolean isEnabled) {
		return new UserDetailsImpl(username, password, authorities, isEnabled);
	}
	
	public static UserDetailsImpl buildUserDetails(UsuarioDTO usuarioDTO, String password,
			Collection<? extends GrantedAuthority> authorities) {
		return new UserDetailsImpl(usuarioDTO.getId(), usuarioDTO.getPersonaDTO(), usuarioDTO.getUsuario(), password,
				authorities, usuarioDTO.getEstadoPersona());
	}
	
	public Long getId() {
		return id;
	}

	public PersonaDTO getPersona() {
		return persona;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.getId());
	}

}
