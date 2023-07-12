package com.indra.cmoff.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indra.cmoff.dto.PermisoRolDTO;
import com.indra.cmoff.dto.PersonaDTO;
import com.indra.cmoff.dto.RolDTO;
import com.indra.cmoff.dto.UsuarioDTO;
import com.indra.cmoff.dto.UsuarioRolDTO;
import com.indra.cmoff.repository.custom.IPersonaDAO;
import com.indra.cmoff.repository.custom.IUsuarioDAO;
import com.indra.cmoff.service.IRolService;
import com.indra.cmoff.service.IUsuarioService;
import com.indra.cmoff.utils.util.Constantes;

@Service
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService {
	
	@Autowired
	PasswordEncoder encoder;
	
	//credenciales SuperAdmin
	@Value("${cmoff.super.name}")
	private String superUserName;
	@Value("${cmoff.super.password}")
	private String superPassword;
	@Value("${cmoff.adm.rol}")
	private String adminRole;
	
	private final IUsuarioDAO usuarioDAO;
	private final IRolService rolService;
	@Autowired
	private IPersonaDAO personaDAO;
	
		public UsuarioServiceImpl(IUsuarioDAO usuarioDAO, IRolService rolService) {
		this.usuarioDAO = usuarioDAO;
		this.rolService = rolService;
	}

	@Override
	public Optional<UsuarioDTO> findById(long id) {
		return usuarioDAO.findById(id);
	}

	@Override
	public List<UsuarioDTO> findAll() {
		return usuarioDAO.findAll();
	}

	@Override
	public List<UsuarioDTO> findAllPaginated(int page, int size) {
		return usuarioDAO.findAllPaginated(page, size).toList();
	}

	@Override
	public Page<UsuarioDTO> filterPaginated(int page, int size, UsuarioDTO entity, String column, String orderType) {
		return usuarioDAO.filterPaginated(page, size, entity, column, orderType);
	}

	@Override
	public long countFilter(UsuarioDTO entity) {
		return usuarioDAO.countFilter(entity);
	}

	@Override
	public UsuarioDTO save(UsuarioDTO usuarioDTO) {
		List<Long> roles = usuarioDTO.getRoles();
		List<UsuarioRolDTO> usuarioRolesDTO = new ArrayList<UsuarioRolDTO>();
		UsuarioDTO usuarioDTOSaved = usuarioDAO.save(usuarioDTO);
		for( Long idRol : roles) {
			RolDTO rolDTO = new RolDTO();
			rolDTO.setId(idRol);
			UsuarioRolDTO usuarioRolDTO = new UsuarioRolDTO(usuarioDTOSaved.getId(), rolDTO);
			usuarioRolesDTO.add(usuarioRolDTO);
		}
		if (usuarioRolesDTO.size() > 0)
			rolService.saveRolesUsuario(usuarioDTOSaved.getId(), usuarioRolesDTO);
		return usuarioDTOSaved;
	}

	@Override
	public void delete(UsuarioDTO entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		usuarioDAO.deleteById(entityId);
	}

	@Override
	public Optional<UsuarioDTO> login(String usuario, String clave) {
		UsuarioDTO u = usuarioDAO.findByUsuarioAndClave(usuario, clave);
		return Optional.ofNullable(u);
	}

	@Override
	public List<UsuarioDTO> saveList(List<UsuarioDTO> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<UsuarioDTO> findByUsuario(String usuario) {
		return usuarioDAO.findByUsuario(usuario);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws AuthenticationServiceException {
		if (StringUtils.equals(username, superUserName)) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_" + adminRole));
			return UserDetailsImpl.buildSuperUser(username, superPassword, authorities, true);
		} 
		else {
			UsuarioDTO usuarioDTO = usuarioDAO.findByUsuario(username)
					.orElseThrow(() -> new AuthenticationServiceException(Constantes.ERROR_USER_BAD_CRED));
			/**
			 * Consulta si la persona existe en DB para validar el acceso al sistema.
			 */
			PersonaDTO personaDTO = personaDAO.findByCodEmpleado(usuarioDTO.getPersona());
			if (ObjectUtils.isNotEmpty(personaDTO)) {
				usuarioDTO.setEstadoPersona(true);
				usuarioDTO.setPersonaDTO(personaDTO);
			} else
				usuarioDTO.setEstadoPersona(false);
			return UserDetailsImpl.buildUserDetails(usuarioDTO, usuarioDAO.getPasswordByUsuarioId(usuarioDTO.getId()),
					getAuthorities(usuarioDTO));
		}
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(UsuarioDTO usuarioDTO) throws AuthenticationServiceException{
		List<PermisoRolDTO> permisos = new ArrayList<PermisoRolDTO>();
		List<PermisoRolDTO> permisosRol;
		List<GrantedAuthority> authorities = new ArrayList<>();
		List<UsuarioRolDTO> usuarioRolesDTO = rolService.findUsuarioRolByIdIdUsuario(usuarioDTO.getId());
		for (UsuarioRolDTO usuarioRolDTO : usuarioRolesDTO) {
			if(usuarioRolDTO.getRol().getEstado()) {
				if (StringUtils.equals(adminRole, usuarioRolDTO.getRol().getCodigo())) {
					authorities.add(new SimpleGrantedAuthority("ROLE_" + usuarioRolDTO.getRol().getCodigo()));
					break;
				}
			}
		}
		if (authorities.isEmpty() || authorities == null) {
			for (UsuarioRolDTO usuarioRolDTO : usuarioRolesDTO) {
				//Si el usuario tiene activo ese rol
				if(usuarioRolDTO.getRol().getEstado()) {
					permisosRol = rolService.findPermisoRolByIdRolId(usuarioRolDTO.getRol().getId());
					for (PermisoRolDTO permisoRolDTO : permisosRol) {
						//modulos y permisos activos para cada rol que tiene el usuario, 
						if(permisoRolDTO.getModulo().getEstado()) {
							if(permisoRolDTO.getModulo().getModuloEnlazado() != null) {
								if(permisoRolDTO.getModulo().getModuloEnlazado().getEstado())
									if (!authorities.contains(new SimpleGrantedAuthority("ROLE_" + permisoRolDTO.getModulo().getCodigo()))) {
										authorities.add(new SimpleGrantedAuthority("ROLE_" + permisoRolDTO.getModulo().getCodigo()));
									}
									permisos.add(permisoRolDTO);
							} else {
								if (!authorities.contains(new SimpleGrantedAuthority("ROLE_" + permisoRolDTO.getModulo().getCodigo()))) {
									authorities.add(new SimpleGrantedAuthority("ROLE_" + permisoRolDTO.getModulo().getCodigo()));
								}
								permisos.add(permisoRolDTO);
							}
						}
					}
				}
			}
		}
		if(!permisos.isEmpty()) {
			for (PermisoRolDTO permisoRolDTO : permisos) {
				if(!authorities.contains(new SimpleGrantedAuthority("ROLE_" + permisoRolDTO.getModulo().getCodigo()
						+ "-"+ permisoRolDTO.getPermiso().getCodigo()))) {
					//si tiene modulo enlazado se evalua que exista el permiso tanto en el padre como en el hijo
					if(permisoRolDTO.getModulo().getModuloEnlazado() != null) {
						PermisoRolDTO permisoRolDTOEnlazado = null;
						for (PermisoRolDTO permisoRolDTO2 : permisos) {
							//si es el modulo padre (enlazado->null) y si (hijo, padre) tienen el mismo modulo
							if(permisoRolDTO2.getModulo().getModuloEnlazado() == null 
									&& StringUtils.equals(permisoRolDTO2.getModulo().getCodigo(), permisoRolDTO.getModulo().getModuloEnlazado().getCodigo()) 
									&& StringUtils.equals(permisoRolDTO2.getPermiso().getCodigo(), permisoRolDTO.getPermiso().getCodigo())) {
								permisoRolDTOEnlazado = permisoRolDTO2;
								break;
							}
						}
						if(permisoRolDTOEnlazado != null && 
								StringUtils.equals(permisoRolDTOEnlazado.getPermiso().getCodigo(), 
										permisoRolDTO.getPermiso().getCodigo())) {
							authorities.add(new SimpleGrantedAuthority(
									"ROLE_" + permisoRolDTO.getModulo().getCodigo() + "-" +
									permisoRolDTO.getPermiso().getCodigo()));
						}
					}else {
						authorities.add(new SimpleGrantedAuthority(
								"ROLE_" + permisoRolDTO.getModulo().getCodigo() + "-" +
								permisoRolDTO.getPermiso().getCodigo()));
					}
				}
			}
		}
		if(authorities.isEmpty()) {
			throw new AuthenticationServiceException(Constantes.ERROR_USER_EMPTY_AUTH);
		}
		return authorities;
	}

	@Override
	public UsuarioDTO findByPersonaCodigoEmpleado(Long codEmpleado) {
		return usuarioDAO.findByPersonaCodigoEmpleado(codEmpleado);
	}
	
}
