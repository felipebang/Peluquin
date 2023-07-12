package com.indra.cmoff.repository.custom;

import java.util.Optional;


import com.indra.cmoff.dto.UsuarioDTO;

public interface IUsuarioDAO extends ICrudDAO<UsuarioDTO>{
	
	UsuarioDTO findByUsuarioAndClave(String usuario, String clave);
	
	Optional<UsuarioDTO> findByUsuario (String usuario);
	
	String getPasswordByUsuarioId (Long usuarioId);

	UsuarioDTO findByPersonaCodigoEmpleado(Long codEmpleado);
}
