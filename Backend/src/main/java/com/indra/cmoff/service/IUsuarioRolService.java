package com.indra.cmoff.service;

import java.util.List;

import com.indra.cmoff.dto.UsuarioRolDTO;

public interface IUsuarioRolService extends ICrudService<UsuarioRolDTO> {
	
	List<UsuarioRolDTO> findByIdIdUsuario(Long idUsuario);

}
