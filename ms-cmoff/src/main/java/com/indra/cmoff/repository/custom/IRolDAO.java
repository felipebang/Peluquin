package com.indra.cmoff.repository.custom;

import java.util.List;
import com.indra.cmoff.dto.RolDTO;

public interface IRolDAO extends ICrudDAO<RolDTO> {
	
	List<RolDTO> findRolDescripcionByIdUsuarioRol(Long idUsuario);
	
	/**
	 * Metodo que permite obtener los Proyectos apartir de un estado
	 *
	 * @autor ddelgadoo
	 * @param estado
	 * @return List<Rol>
	 */
	List<RolDTO> findByEstado(Boolean estado);
	
	/**
	 * Metodo que permite obtener un rol a partir del codigo
	 * 
	 * @param codigo
	 * @return Rol
	 * @autor shenao
	 * 
	 **/
	RolDTO findByCodigo (String codigo);

}
