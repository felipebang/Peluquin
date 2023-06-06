package com.indra.cmoff.service;

import java.util.Optional;

import com.indra.cmoff.dto.UsuarioDTO;

public interface IUsuarioService extends ICrudService<UsuarioDTO>{

	Optional<UsuarioDTO> login(String usuario, String clave);

	Optional<UsuarioDTO> findByUsuario (String usuario);

	UsuarioDTO findByPersonaCodigoEmpleado(Long codEmpleado);
	
}
