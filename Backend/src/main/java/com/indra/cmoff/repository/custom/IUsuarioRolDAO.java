package com.indra.cmoff.repository.custom;

import java.util.List;
import com.indra.cmoff.dto.UsuarioRolDTO;

public interface IUsuarioRolDAO extends ICrudDAO<UsuarioRolDTO>{
	/**
	 * Metodo que permite obtener lista de UsuarioRoles apartir del id del usuario
	 * @autor ddelgadoo
	 * @param idUsuario
	 * @return List<UsuarioRol>
	 */
	List<UsuarioRolDTO> findByIdIdUsuario(Long idUsuario);

	void eliminarRolesPorIdUsuario(Long idUser);
}
